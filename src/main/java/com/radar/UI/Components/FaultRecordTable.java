package com.radar.UI.Components;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.radar.SpringUtil;
import com.radar.Entity.Fault;
import com.radar.ServiceImpl.FaultRecordServiceImpl;

public class FaultRecordTable extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JTable表分页信息相关变量
    public int currentPage = 1;
    private int pageCount = 10;
    private int totalPage = 0;
    private int totalRowCount = 0;
    private int column = 0;
    private int restCount;
    
   	//表格加载的数据
       private Object[][] resultData;
       private List<Fault> faults =  new ArrayList<Fault>();
       public String[] columnNames = { "recordId","序号", "雷达型号", "开机时间", "关机时间" ,"活动目的","是否故障"};
       private DefaultTableModel model = null;
      
       public FaultRecordTable(){
       	initTable();

   	}

	private void initTable() {
		// TODO Auto-generated  stub
		new SwingWorkerFaultRecord().execute();
		setBorder(new LineBorder(new Color(0, 0, 0)));	
		 Object[][] data = getFaultRecordData(faults);
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
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        setDefaultRenderer(Object.class, r);
	}
	
	class SwingWorkerFaultRecord extends SwingWorker<List<Fault>,Void>{
		//SwingWorker线程执行数据库查询操作	
		  //在非EDT线程执行
	  @SuppressWarnings("static-access")
	  @Override
	  protected List<Fault> doInBackground() throws Exception {
		  SpringUtil s = new SpringUtil();
		  FaultRecordServiceImpl faultRecordServiceImpl = (FaultRecordServiceImpl) s.getBean("FaultRecordServiceImpl"); 
		  List<Fault> f = faultRecordServiceImpl.getAllFaultRecords();
		  System.out.println(f);
		  publish();
		  return f; 
	  }
	  
	  protected void process() {
	  
	  }
	 
	  //EDT线程执行table数据载入，并更新操作
	  protected void done() {
		  try { 
			  faults = get();
		      Object[][] data = getFaultRecordData(faults);

			  if(data != null){
				  initResultData(data);
				  model = new DefaultTableModel(getPageData(), columnNames);

			  }else {
		            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
		            Object[][] nothing = { {},{}, {}, {}, {}, {} ,{}};
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
	public Object[][] getFaultRecordData(List<Fault> faults) {
		// TODO Auto-generated method stub
		if(faults.size()>0){
			Object[][] data=new Object[faults.size()][5];
			int dataCounts = 0;
			for(int i =0;i<faults.size();i++){
				Fault f = faults.get(i);
				

				Object[] E = {f.getFaultId(),dataCounts+1,f.getRecordId().getRadarId().getRadarName(),f.getFaultTypeId().getFaultName(),f.getFaultDate(),f.getFaultLocation(),f.getFaultReason()};
				data[dataCounts] = E;
				dataCounts++;
			}
			Object[][] dataResult = new Object[dataCounts][5];
			for(int i =0;i<dataCounts;i++){
				dataResult[i] = data[i];
			}
			return dataResult;
		}
		return null;	
		}
	public void initResultData(Object[][] data) {
		// TODO Auto-generated method stub
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

	public void setTablePreferredWidthAndPreferredHeight(FaultRecordTable faultRecordTable) {
		// TODO Auto-generated method stub
		if(faultRecordTable == null)
			faultRecordTable = this;
	    //设置每一行的行高
		faultRecordTable.setRowHeight(30);
	    //设置每一列的列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		//设置表格第一列不可见（该列存储雷达的radarId）
		faultRecordTable.getColumnModel().getColumn(0).setMinWidth(0);
		faultRecordTable.getColumnModel().getColumn(0).setWidth(0);
		faultRecordTable.getColumnModel().getColumn(0).setMaxWidth(0);
		faultRecordTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		faultRecordTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		faultRecordTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		faultRecordTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		faultRecordTable.getColumnModel().getColumn(4).setPreferredWidth(120);
		faultRecordTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		faultRecordTable.getColumnModel().getColumn(6).setPreferredWidth(70);

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

	public Object[][] getPageData() {
		// TODO Auto-generated method stub
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
	        return currentPageData;	}

	public void getFaultRecordByRadarAndTime(String radarNumber, String faultType, String faultDateText) {
		// TODO Auto-generated method stub
		Object[][] data = getFaultDataByConditions(faults,radarNumber,faultType,faultDateText);
    	if (data != null) {
			  initResultData(data);
			  model = new DefaultTableModel(getPageData(), columnNames);
		  } 
		  else { 
			  //如果结果集中没有数据，那么就用空来代替数据集中的每一行 
			  Object[][] nothing = { {},{}, {}, {}, {}, {},{} };
			  model = new DefaultTableModel(nothing, columnNames);
			  totalRowCount = 0;
		  }
		  setModel(model);
		  setTablePreferredWidthAndPreferredHeight(null);
	}

	private Object[][] getFaultDataByConditions(List<Fault> fault, String radarNumber, String faultType,
			String faultDateText) {
		// TODO Auto-generated method stub
		if(fault.size()>0){
			  Object[][] data = new Object[fault.size()][5];
	            //data的first size;
	            int dataCounts = 0;
	            for(int i=0;i<fault.size();i++){
	            	Fault f = fault.get(i);
	            	Boolean addItems = false;
	            	//查询结果中的雷达编号
	            	String HRadarNumber = f.getRecordId().getRadarId().getRadarName();
	            	//查询结果中的故障类型
	            	String HFaultName = f.getFaultTypeId().getFaultName();
	            	
	            	Date BFaultDate = f.getFaultDate();
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            	//查询结果中的故障发生时刻
	            	String HFaultDate = sdf.format(BFaultDate); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串

	            	//只有雷达编号
	            	if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
      			   &&(faultType==null||faultType.equals(""))
          		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            	//只有故障类型
	            
	            	else if((faultType==HFaultName||faultType.equals(HFaultName))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;
	            	}
	            	//只有故障发生时刻
	            	else if((faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(faultType==null||faultType.equals(""))){
	            		addItems=true;

	            	}
	            	//雷达编号、故障类型、时刻
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultType==HFaultName||faultType.equals(HFaultName))
		            		   &&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))){
	            		addItems=true;

	            	}
	            	//只有雷达编号、故障时刻
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
		            		   &&(faultType==null||faultType.equals(""))){
	            		addItems=true;
	            	}
	            	//只有雷达编号、故障类型
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultType==HFaultName||faultType.equals(HFaultName))
		            		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            	//只有故障类型、故障时刻
	            	else if((faultType==HFaultName||faultType.equals(HFaultName))
	            			&&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
	            			&&(radarNumber==null||radarNumber.equals(""))){
	            		addItems=true;
	            	}
	            	else if((radarNumber==null||radarNumber.equals(""))
	            			&&(faultType==null||faultType.equals(""))
	            			&&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            	if(addItems) {
	    				Object[] a = {f.getFaultId(),dataCounts+1,f.getRecordId().getRadarId().getRadarName(),f.getFaultTypeId().getFaultName(),f.getFaultDate(),f.getFaultLocation(),f.getFaultReason()};
	                    data[dataCounts] = a;// 把数组的值赋给二维数组的一行
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


}
