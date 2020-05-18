package com.radar.UI.Components;

import java.awt.Color;
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
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;

@SuppressWarnings("serial")
public class RadarTable extends JTable {
    // JTable表分页信息相关变量
    public int currentPage = 1;
    private int pageCount = 10;
    private int totalPage = 0;
    private int totalRowCount = 0;
    private int column = 0;
    private int restCount;
    //表格加载的数据
    private Object[][] resultData;
    private List<Radar> radars =  new ArrayList<Radar>();
    public String[] columnNames = { "radarId","序号", "雷达型号", "所属部队", "雷达编号" ,"操作"};
    private DefaultTableModel model = null;

    public RadarTable() {
    	initTable();
    }
    
    //根据managerName和type两个条件，获取表格
    public void getPageByManagerNameAndType(String managerName,String type) {
    	Object[][] data = getData(radars,managerName,type);
		  if (data != null) {
			  initResultData(data);
			  model = new DefaultTableModel(getPageData(), columnNames);
		  } 
		  else { 
			  //如果结果集中没有数据，那么就用空来代替数据集中的每一行 
			  Object[][] nothing = { {},{}, {}, {}, {}, {}, {},{}, {}, {}, {}, {} };
			  model = new DefaultTableModel(nothing, columnNames);
			  totalRowCount = 0;
		  }
		  setModel(model);
		  setTablePreferredWidthAndPreferredHeight(null);
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
     * 根据managerName和type两个条件，获取表格的加载数据集
     * 
     * @param radar
     * @return
     */
    public Object[][] getData(List<Radar> radar,String managerName,String type) {
        if (radar.size() > 0) {
            Object[][] data = new Object[radar.size()][6];
            //data的first size;
            int dataCounts = 0;
            //根据managerName和type从数据库查询结果radar选取符合条件的记录，插入到表格数据集中
            for (int i = 0; i < radar.size();i++) {
            	Radar r = radars.get(i);
            	//雷达型号
            	String radarType;
            	if(r.getType() == 0)
            		radarType = "I型雷达";
            	else
            		radarType = "II型雷达";
            	String radarManager = r.getManagerId().getManagerName();
            	//本次循环是否添加本条纪录
            	Boolean addItems = false;
            	//managerName为All，type为All（选取所有的记录）
            	if((managerName == "All" || managerName.equals("All"))&&(type == "All" || type.equals("All"))) {
            		addItems = true;
            	}
            	//managerName不为All，type为All(选取某一部队的所有型号雷达的数据)
            	else if((managerName != "All" || !managerName.equals("All"))&&(type == "All" || type.equals("All"))) {
            		if(managerName == radarManager || managerName.equals(radarManager))
            			addItems = true;
            	}
            	//managerName为All，type不为All(选取某一型号的所有雷达的数据)
            	else if((managerName == "All" || managerName.equals("All"))&&(type != "All" || !type.equals("All"))) {
            		if(type == radarType || type.equals(radarType))
            			addItems = true;
            	}            	
            	//managerName不为All，type不为All(选取某一部队某一型号的雷达的数据)
            	else if((managerName == radarManager || managerName.equals(radarManager))
            			&&(type == radarType || type.equals(radarType))) {
            		addItems = true;            		
            	}
            	if(addItems) {
            		Object[] a = { r.getRadarId(),dataCounts+1,radarType,radarManager,r.getRadarName(),"修改" };
                    data[dataCounts] = a;// 把数组的值赋给二维数组的一行
                    dataCounts++;
            	}
            }
            Object[][] result = new Object[dataCounts][4];
            for(int i=0;i<dataCounts;i++) {
            	result[i] = data[i];
            }
            return result;
        }
        return null;
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
    
	
    public void setTablePreferredWidthAndPreferredHeight(RadarTable table) {
    	
    	if(table == null)
    		table = this;
	    //设置每一行的行高
		table.setRowHeight(30);
	    //设置每一列的列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		//设置表格第一列不可见（该列存储雷达的radarId）
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(160);
		
	}
    /**
     * 初始化表格数据
     */
    public void initTable() {
		new SwingWorker1().execute();
		setBorder(new LineBorder(new Color(0, 0, 0)));		
        Object[][] data = getData(radars,"All","All");
        if (data != null) {
            initResultData(data);
            model = new DefaultTableModel(getPageData(), columnNames);
        } else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {},{}, {}, {}, {}, {} };
            model = new DefaultTableModel(nothing, columnNames);
            totalRowCount = 0;
        }
        this.setModel(model);
        setTablePreferredWidthAndPreferredHeight(this);
        System.out.println("first:"+Thread.currentThread().getName());
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
    }
  
	  class SwingWorker1 extends SwingWorker<List<Radar>,Void>{
		  
		  //SwingWorker线程执行数据库查询操作	
		  //在非EDT线程执行
	  @SuppressWarnings("static-access")
	  @Override
	  protected List<Radar> doInBackground() throws Exception {
		  SpringUtil s = new SpringUtil();
		  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
		  List<Radar> r = radarServiceImpl.getAllRadars();
		  publish();
		  return r; 
	  }
	  
	  protected void process() {
	  
	  }
	 
	  //EDT线程执行table数据载入，并更新操作
	  protected void done() {
		  try { 
			  radars = get();
			  Object[][] data = getData(radars,"All","All");
			  if (data != null) {
				  initResultData(data);
				  model = new DefaultTableModel(getPageData(), columnNames);
			  } 
			  else { 
				  //如果结果集中没有数据，那么就用空来代替数据集中的每一行 
				  Object[][] nothing = { {}, {}, {}, {}, {} };
				  model = new DefaultTableModel(nothing, columnNames);
				  totalRowCount = 0;
			  }
			  setModel(model);
			  setTablePreferredWidthAndPreferredHeight(null);
		  }
		  catch(InterruptedException e) { 
			  e.printStackTrace();
		  } catch (ExecutionException e) { 
			  e.printStackTrace();
		  } 
	  }
	  }
	 
}


