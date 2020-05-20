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
import com.radar.Entity.Record;
import com.radar.ServiceImpl.RecordServiceImpl;

public class ActivityRecordTable extends JTable{
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
    private List<Record> records =  new ArrayList<Record>();
    public String[] columnNames = { "recordId","序号", "雷达型号", "开机时间", "关机时间" ,"活动目的","是否故障"};
    private DefaultTableModel model = null;

	public ActivityRecordTable(){
    	initTable();

	}

	private void initTable() {
		// TODO Auto-generated method stub
		new SwingWorkerRecord().execute();
		setBorder(new LineBorder(new Color(0, 0, 0)));	
		 Object[][] data = getRecordData(records);
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
	class SwingWorkerRecord extends SwingWorker<List<Record>,Void>{
		//SwingWorker线程执行数据库查询操作	
		  //在非EDT线程执行
	  @SuppressWarnings("static-access")
	  @Override
	  protected List<Record> doInBackground() throws Exception {
		  SpringUtil s = new SpringUtil();
		  RecordServiceImpl recordServiceImpl = (RecordServiceImpl) s.getBean("RecordServiceImpl"); 
		  List<Record> r = recordServiceImpl.getAllRecords();
		  System.out.println(r);
		  publish();
		  return r; 
	  }
	  
	  protected void process() {
	  
	  }
	 
	  //EDT线程执行table数据载入，并更新操作
	  protected void done() {
		  try { 
			  records = get();
		      Object[][] data = getRecordData(records);

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
	public Object[][] getRecordData(List<Record>records) {
		// TODO Auto-generated method stub
		if(records.size()>0){
			Object[][] data=new Object[records.size()][5];
			int dataCounts = 0;
			for(int i =0;i<records.size();i++){
				String	IsDefault;
				Record r = records.get(i);
				if(r.getWithFault()==0){
					IsDefault="否";
				}else{
					IsDefault="是";

				}

				Object[] E = {r.getRecordId(),dataCounts+1,r.getRadarId().getRadarName(),r.getRecordStartDate(),r.getRecordEndDate(),r.getActivityId().getActivityName(),IsDefault};
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

	public void setTablePreferredWidthAndPreferredHeight(ActivityRecordTable activityRecordTable) {
		// TODO Auto-generated method stub
		if(activityRecordTable == null)
			activityRecordTable = this;
	    //设置每一行的行高
		activityRecordTable.setRowHeight(30);
	    //设置每一列的列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		//设置表格第一列不可见（该列存储雷达的radarId）
		activityRecordTable.getColumnModel().getColumn(0).setMinWidth(0);
		activityRecordTable.getColumnModel().getColumn(0).setWidth(0);
		activityRecordTable.getColumnModel().getColumn(0).setMaxWidth(0);
		activityRecordTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		activityRecordTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		activityRecordTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		activityRecordTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		activityRecordTable.getColumnModel().getColumn(4).setPreferredWidth(120);
		activityRecordTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		activityRecordTable.getColumnModel().getColumn(6).setPreferredWidth(70);

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
	 public void initResultData(Object[][] data) {
	        if (data != null) {
	        	System.out.println("data长度："+data[0].length);
	        	System.out.println("data长度："+data.length);

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

		public void getRecordByRadarAndTime(String radarNumber, String startTime, String endTime) {
			// TODO Auto-generated method stub
	    	Object[][] data = getDataByConditions(records,radarNumber,startTime,endTime);
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

		private Object[][] getDataByConditions(List<Record> record, String radarNumber, String startTime,
				String endTime) {
			// TODO Auto-generated method stub
			if(record.size()>0){
				  Object[][] data = new Object[record.size()][5];
		            //data的first size;
		            int dataCounts = 0;
		            for(int i=0;i<record.size();i++){
		            	Record r = record.get(i);
		            	Boolean addItems = false;
		            	String IsDefault;
		            	String HRadarNumber = r.getRadarId().getRadarName();
		            	Date BRadarStartTime = r.getRecordStartDate();
		            	Date BRadrEndTime = r.getRecordEndDate();
	            	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                  String HRadarStartTime = sdf.format(BRadarStartTime); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
	                  String HRadarEndTime = sdf.format(BRadrEndTime); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串


		            	if(r.getWithFault()==0){
		            		IsDefault="否";
		            	}else{
		            		IsDefault="是";

		            	}
		            	
		            	//只有雷达编号
		            	if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
            			   &&(startTime==null||startTime.equals(""))
	            		   &&(endTime==null||endTime.equals(""))){
		            		addItems=true;
		            	}
		            	//只有开机时间
		            
		            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
		            			   &&(radarNumber==null||radarNumber.equals(""))
			            		   &&(endTime==null||endTime.equals(""))){
		            		addItems=true;
		            	}
		            	//只有关机时间
		            	else if((startTime==null||startTime.equals(""))
		            			   &&(radarNumber==null||radarNumber.equals(""))
			            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
		            		addItems=true;

		            	}
		            	//雷达编号、开机时间、关机时间都有
		            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
		            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
			            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
		            		addItems=true;

		            	}
		            	//只有雷达编号、开机时间
		            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
		            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
			            		   &&(endTime==null||endTime.equals(""))){
		            		addItems=true;
		            	}
		            	//只有雷达编号、关机时间
		            	else if((startTime==null||startTime.equals(""))
		            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
			            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
		            		addItems=true;

		            	}
		            	//只有开机时间、关机时间
		            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
		            			&&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))
		            			&&(radarNumber==null||radarNumber.equals(""))){
		            		addItems=true;
		            	}
		            	else if((startTime==null||startTime.equals(""))
		            			&&(endTime==null||endTime.equals(""))
		            			&&(radarNumber==null||radarNumber.equals(""))){
		            		addItems=true;

		            	}
		            	if(addItems) {
		    				Object[] a = {r.getRecordId(),dataCounts+1,r.getRadarId().getRadarName(),r.getRecordStartDate(),r.getRecordEndDate(),r.getActivityId().getActivityName(),IsDefault};
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
