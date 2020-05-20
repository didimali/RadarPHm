package com.radar.UI.Components;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.radar.SpringUtil;
import com.radar.Entity.DynamicData;
import com.radar.ServiceImpl.DynamicDataServiceImpl;

@SuppressWarnings("serial")
public class DynamicDataTable extends JTable{
	
	 // JTable表分页信息相关变量
    private int currentPage = 1;
    private int pageCount = 10;
    private int totalPage = 0;
    private int totalRowCount = 0;
    private int column = 0;
    private int restCount;
    //表格加载的数据
    private Object[][] resultData;
    private List<DynamicData> dynamicData =  new ArrayList<DynamicData>();
    public String[] columnNames = {"序号", "雷达编号", "参数", "参数值" ,"采集时间"};
    private DefaultTableModel model = null;
    
    private String startDate;
    private String endDate;
	
	public DynamicDataTable(String sDate, String eDate) {
		setStartDate(sDate);
		setEndDate(eDate);
		initTable();
		
	}
	
	/**
	 * 初始化表格数据 */
	private void initTable() {
		//数据库查询数据
		SwingWorkerForDynamicDataTable swfddt = new SwingWorkerForDynamicDataTable();
		swfddt.setStartDate(this.startDate);
		swfddt.setEndDate(this.endDate);
		swfddt.execute();
		//表格初始化
		setBorder(new LineBorder(new Color(0, 0, 0)));	
		refreshTable(startDate,endDate,null,null);
        System.out.println("first:"+Thread.currentThread().getName());
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
	}

	/**
     * 根据radars或radar获取表格的加载数据集
     */
    public Object[][] getData(List<DynamicData> dynamicData,String[] radars,String radar) {
        if (dynamicData.size() > 0) {
            Object[][] data = new Object[dynamicData.size()][5];
            int dataCounts = 0;
            //根据managerName和type从数据库查询结果radar选取符合条件的记录，插入到表格数据集中
            for (int i = 0; i < dynamicData.size();i++) {
            	DynamicData d = dynamicData.get(i);
            	String radarName = d.getRadarId().getRadarName();
            	String paramName = d.getBasicInfoId().getParamName();
            	String paramValue = d.getDataValue();
            	SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化当前系统日期 
                String collectDate = dateFm.format(d.getCollectDate());  
                if(radars != null) {
                	for(int j=0;j<radars.length;j++) {
                		if(radarName == radars[j] || radarName.equals(radars[j])) {
                			Object[] a = {dataCounts+1,radarName,paramName,paramValue,collectDate};
                            data[dataCounts] = a;// 把数组的值赋给二维数组的一行
                            dataCounts++;
                            break;
                		}
                	}
                }
                else if(radar != null) {
                	if(radar == radarName || radar.equals(radarName)) {
                		Object[] a = {dataCounts+1,radarName,paramName,paramValue,collectDate};
                        data[dataCounts] = a;// 把数组的值赋给二维数组的一行
                        dataCounts++;
                	}                	
                }
                else {
                	Object[] a = {dataCounts+1,radarName,paramName,paramValue,collectDate};
                    data[i] = a;// 把数组的值赋给二维数组的一行
                    dataCounts++;
                }
        		
            }
            Object[][] result = new Object[dataCounts][5];
            for(int i=0;i<dataCounts;i++) {
            	result[i] = data[i];
            }
            return result;
        }
        return null;
    }
    /**
     * 根据radars或者radar刷新表格
     * @author madi
     * @param startDate 起始时间
     * @param endDate 截止时间 
     * @param radars 多台雷达名字数组
     * @param radar 单台雷达名字
     */
    public void refreshTable(String startDate,String endDate,String[] radars,String radar) {
    	//如果未更换时间段，使用上次查询结果
    	if(startDate == this.startDate && endDate == this.endDate) {
    		Object[][] data = getData(dynamicData,radars,radar);
        	if (data != null && data.length !=0) {
                initResultData(data);
                model = new DefaultTableModel(getPageData(), columnNames);
            } else {
                // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
                Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}};
                model = new DefaultTableModel(nothing, columnNames);
                totalRowCount = 0;
            }
        	this.setModel(model);
        	setTablePreferredWidthAndPreferredHeight(null);
    	}
    	//否则，使用新时间段，查询数据库结果
    	else {
    		setStartDate(startDate);
    		setEndDate(endDate);
    		//数据库查询数据
    		SwingWorkerForDynamicDataTable swfddt1 = new SwingWorkerForDynamicDataTable();
    		swfddt1.setStartDate(startDate);
    		swfddt1.setEndDate(endDate);
    		swfddt1.setRadar(radar);
    		swfddt1.setRadars(radars);
    		swfddt1.execute();
    	}
    	
    	
    };

    //获取动态数据
	class SwingWorkerForDynamicDataTable extends SwingWorker<List<DynamicData>,Void>{
		private String startDate;
		private String endDate;
		private String radar = null;
		private String[] radars = null;

		@SuppressWarnings("static-access")
		@Override
		protected List<DynamicData> doInBackground() throws Exception {
			SpringUtil s  = new SpringUtil();
			DynamicDataServiceImpl dynamicDataServiceImpl = 
					(DynamicDataServiceImpl) s.getBean("DynamicDataServiceImpl");
			List<DynamicData> result = dynamicDataServiceImpl.getAllDynamicDataByCollectDate(startDate,endDate);
			return result;
		}
		
		@Override
		protected void done() {
			try {
				dynamicData = get();
				refreshTable(getStartDate(),getEndDate(),getRadars(),getRadar());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getRadar() {
			return radar;
		}

		public void setRadar(String radar) {
			this.radar = radar;
		}

		public String[] getRadars() {
			return radars;
		}

		public void setRadars(String[] radars) {
			this.radars = radars;
		}
		
	}
    public void initResultData(Object[][] data) {
        if (data != null) {
            resultData = data;// 总的结果集
            column = data[0].length;// 表的列数
            totalRowCount = data.length;// 表的长度
            currentPage = 1;
            totalPage = totalRowCount % pageCount == 0 ? totalRowCount
                    / pageCount : totalRowCount / pageCount + 1;// 结果集的总页数
            restCount = totalRowCount % pageCount == 0 ? 10 : totalRowCount
                    % pageCount;// 最后一页的数据数
        }
    }
    /**
     * 获取分页数据
     * 
     * @return
     */
    public Object[][] getPageData() {
        Object[][] currentPageData = new Object[pageCount][column];// 构造每页数据集
        if (this.getCurrentPage() < this.totalPage) {// 如果当前页数小于总页数，那么每页数目应该是规定的数pageCount
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + pageCount; i++) {
                for (int j = 0; j < column; j++) {
                    // 把结果集中对应每页的每一行数据全部赋值给当前页的每一行的每一列
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        } else {
            // 在动态改变数据结果集的时候，如果当前页没有数据了，则回到前一页（一般针对最后一页而言）
            if (pageCount * (this.getCurrentPage() - 1) >= totalRowCount)
                this.currentPage--;
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + restCount; i++) {
                for (int j = 0; j < column; j++) {
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        }
        return currentPageData;
    }
    public void setTablePreferredWidthAndPreferredHeight(DynamicDataTable table) {
    	
    	if(table == null)
    		table = this;
	    //设置每一行的行高
		table.setRowHeight(30);
	    //设置每一列的列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		//设置表格第一列不可见（该列存储雷达的radarId）
//		table.getColumnModel().getColumn(0).setMinWidth(0);
//		table.getColumnModel().getColumn(0).setWidth(0);
//		table.getColumnModel().getColumn(0).setMaxWidth(0);
//		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		
	}
	 /**
     * 获取下一页
     */
    public int getNextPage() {
        if (this.currentPage != this.totalPage) {
            return ++currentPage;
        }
        return -1;
    }

    /**
     * 获取上一页
     */
    public int getPreviousPage() {
        if (this.currentPage != 1) {
            return --currentPage;
        }
        return -1;
    }

    /**
     * 获取最后一页
     */
    public int getLastPage() {
    	currentPage = totalPage;
        return this.totalPage;
    }

    /**
     * 获取第一页
     */
    public int getFirstPage() {
    	currentPage = 1;
        return 1;
    }

    /**
     * 获取总页数
     */
    public int getTotolPage() {
        return this.totalPage;
    }

    /**
     * 获取当前页
     */
    public int getCurrentPage() {
        return this.currentPage;
    }
    /**
     * 设置数据起始时间
     * @param startDate
     */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 设置数据截止时间
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
