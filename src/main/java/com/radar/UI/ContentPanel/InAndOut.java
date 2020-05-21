package com.radar.UI.ContentPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.radar.SpringUtil;
import com.radar.Entity.Activity;
import com.radar.Entity.DynamicData;
import com.radar.Entity.Fault;
import com.radar.Entity.Manager;
import com.radar.Entity.Radar;
import com.radar.Entity.Record;
import com.radar.Entity.faultType;
import com.radar.ServiceImpl.ActivityServiceImpl;
import com.radar.ServiceImpl.BasicInfoServiceImpl;
import com.radar.ServiceImpl.DynamicDataServiceImpl;
import com.radar.ServiceImpl.FaultRecordServiceImpl;
import com.radar.ServiceImpl.FaultTypeServiceImpl;
import com.radar.ServiceImpl.ManagerServiceImpl;
import com.radar.ServiceImpl.RadarServiceImpl;
import com.radar.ServiceImpl.RecordServiceImpl;
import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.CalendarPanel;
import com.radar.UI.Components.JComBoxForRadarNumber;
import com.radar.UI.Components.JComoBoxForManager;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.JxlWriteException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class InAndOut extends ContentPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//页面组件
	//标题
	private JLabel LabelForTitle;
	//分割线
	private JSeparator separator;
	
	//雷达所属部队标签及下拉框
	private JLabel RadarManager;
	private JComoBoxForManager ManagerName;
	//雷达编号及下拉框
	private JLabel RadarNumber;
	private JComBoxForRadarNumber RadarName;
	
	//数据类型及下拉框
	private JLabel DataType;
	@SuppressWarnings("rawtypes")
	private JComboBox  TypeName;
	//起始时间
	private JLabel StartTime;
	private	JTextField SaveStartTime;
	//终止时间
	private JLabel EndTime;
	private	JTextField SaveEndTime;
	
	//导入导出按钮
	private BottomButtonForTable InButton;
	private BottomButtonForTable OutButton;

	 public static File chooseFile;  
     private static JFileChooser fileChooser;
	
	
	

	
	/**
	 * 导入导出
	 */
	public InAndOut() {
		initUI();
		Action();
	}







	private void Action() {
		// TODO Auto-generated method stub
		InButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String dateType=(String)TypeName.getSelectedItem();
			//导入开机记录
			boolean flagk =false;
			boolean flagg =false;
			boolean flagj = false;
			if(dateType.equals("")) {
				JOptionPane.showMessageDialog(null, "请选择导入的数据类型", "标题",JOptionPane.WARNING_MESSAGE);  
			}else if(dateType.equals("开机记录")) {//kaiji
				  fileChooser = new JFileChooser();             
			        //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
			        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
			        fileChooser.setFileFilter(filter);      
			        int returnValue = fileChooser.showOpenDialog(null);    
			        //弹出一个文件选择提示框
			        if (returnValue == fileChooser.APPROVE_OPTION) {
			        //当用户选择文件后获取文件路径
			        chooseFile = fileChooser.getSelectedFile();
			        
			        //根据文件路径初始化Excel工作簿
			        Workbook workBook=null;
			         try {
			                 workBook = Workbook.getWorkbook(chooseFile);
			         } catch (Exception event) {	
			        	 event.printStackTrace();
			 
			 
			         } 
			          //获取该工作表中的第一个工作表   
			          Sheet sheet=workBook.getSheet(0);  
			          //获取该工作表的行数，以供下面循环使用   
			          int rowSize=sheet.getRows();  
			          System.out.println("开机记录行数:"+rowSize);
			          SpringUtil s = new SpringUtil();
		    		  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
		    		  List<Radar> r = radarServiceImpl.getAllRadars();
		    		  ActivityServiceImpl activityServiceImpl =(ActivityServiceImpl)s.getBean("ActivityServiceImpl");
		    		 List<Activity> a =activityServiceImpl.getAllActivity();
			        if(rowSize>1) {
			   		 for(int i=1;i<rowSize;i++) {
			    		  Record record = new Record();
			        	  String RName = sheet.getCell(0,i).getContents();
			        	  Cell startTime = sheet.getCell(1, i);
			        	  if(startTime.getType()==CellType.DATE) {
			        		  DateCell dcs = (DateCell) startTime;
			                     java.util.Date startDate = dcs.getDate();

			                     TimeZone gmt = TimeZone.getTimeZone("GMT");
			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());

			                     sdf.setTimeZone(gmt);
				                String sDate = sdf.format(startDate);

				                TimeZone local = TimeZone.getDefault();
				                sdf.setTimeZone(local);

					        	  java.util.Date sd = null;

					        	  try {
									sd = sdf.parse(sDate);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        	  record.setRecordStartDate(sd);

			        	  }else {
				        	  String strStartTime = sheet.getCell(1,i).getContents().toString();
				        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        	  java.util.Date startDate = null;
			        		  try {
								startDate = sdf.parse(strStartTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  record.setRecordStartDate(startDate);

			        	  }
			        	  Cell endTime = sheet.getCell(2, i);
			        	  if(endTime.getType()==CellType.DATE) {
			        		  DateCell dcs = (DateCell) endTime;
			                     java.util.Date endDate = dcs.getDate();
			                     TimeZone gmt = TimeZone.getTimeZone("GMT");

			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			                     sdf.setTimeZone(gmt);


				                String eDate = sdf.format(endDate);
				                TimeZone local = TimeZone.getDefault();
				                sdf.setTimeZone(local);
					        	  java.util.Date ed = null;
					        	  try {
									ed = sdf.parse(eDate);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        	  record.setRecordEndDate(ed);

			        	  }else {
				        	  String strEndTime = sheet.getCell(1,i).getContents().toString();
				        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        	  java.util.Date endDate = null;
				        	  try {
								endDate = sdf.parse(strEndTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  record.setRecordEndDate(endDate);

			        	  }
			        	  String activityTarget = sheet.getCell(3,i).getContents();
			        	  String isDefault = sheet.getCell(4,i).getContents();
			        	  if(r!=null||r.size()>0) {
			        		  for(int j=0;j<r.size();j++) {
			    			  System.out.println(r.get(j).getRadarName().toString());
			    			  if(r.get(j).getRadarName().toString().equals(RName)||r.get(j).getRadarName().toString()==RName) {
			    				  record.setRadarId(r.get(j)); 
			    			  }
			    		  }
			        	  }
			    		  
			    		  for(int k =0;k<a.size();k++) {
			    			  if(a.get(k).getActivityName().toString().equals(activityTarget)||a.get(k).getActivityName().toString()==activityTarget) {
			    				  record.setActivityId(a.get(k));
			    			  }
			    		  }
			    		 if(isDefault.equals("是")) {
			    			 record.setWithFault(1);
			    			 
			    		 }else if(isDefault.equals("否")){
			    			 record.setWithFault(0);

			    		 }
			        	 //添加record 
			    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
			    		 flagk = recordServiceImpl.add(record);
			    		 
			          }
			        }
		    
			          if(flagk) {
				            JOptionPane.showMessageDialog(null, "开机记录成功导入");
				            flagk = true;
			    			 System.out.println("成功存储开机记录");
			    		 }
			        }
				
			//kaiji	end
			  //导入故障记录      
			}else if(dateType.equals("故障记录")) {
				fileChooser = new JFileChooser();             
		        //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
		        fileChooser.setFileFilter(filter);      
		        int returnValue = fileChooser.showOpenDialog(null);    
		        //弹出一个文件选择提示框
		        if (returnValue == fileChooser.APPROVE_OPTION) {
		        //当用户选择文件后获取文件路径
		        chooseFile = fileChooser.getSelectedFile();
		        
		        //根据文件路径初始化Excel工作簿
		        Workbook workBook=null;
		         try {
		                 workBook = Workbook.getWorkbook(chooseFile);
		         } catch (Exception event) {	
		        	 event.printStackTrace();
		 
		 
		         } 
		          //获取该工作表中的第一个工作表   
		          Sheet sheet=workBook.getSheet(0);  
		          //获取该工作表的行数，以供下面循环使用   
		          int rowSize=sheet.getRows();  
		          System.out.println("故障记录行数:"+rowSize);
		          SpringUtil s = new SpringUtil();
		          RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
		          FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
		          List<Record> records =recordServiceImpl.getAllRecords();
		          FaultTypeServiceImpl faultTypeServiceImpl =(FaultTypeServiceImpl)s.getBean("FaultTypeServiceImpl");
		          List<faultType> faultTypes = faultTypeServiceImpl.getAllFaultType();
		          for(int i=1;i<rowSize;i++) {
		        	  Fault fault = new Fault();
		        	  String RName = sheet.getCell(0,i).getContents().toString();
		        	  String faultType = sheet.getCell(1,i).getContents().toString();
		        	  Cell faultDate = sheet.getCell(2, i);
		        	  String faultLocation = sheet.getCell(3,i).getContents().toString();
		        	  String faultReason = sheet.getCell(4,i).getContents().toString();


		        	  java.util.Date fd = null;

		        	  if(faultDate.getType()==CellType.DATE) {
		        		  DateCell dcs = (DateCell) faultDate;
		                     java.util.Date fDate = dcs.getDate();
		                     TimeZone gmt = TimeZone.getTimeZone("GMT");

		                     SimpleDateFormat sdf = new SimpleDateFormat(
		                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		                     sdf.setTimeZone(gmt);


			                String faultTime = sdf.format(fDate);
			                TimeZone local = TimeZone.getDefault();
			                sdf.setTimeZone(local);
				        	  try {
				        		  fd = sdf.parse(faultTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  	fault.setFaultDate(fd);
		        	  }
		        	  if(records!=null||records.size()>0) {
		        		  for(int j =0;j<records.size();j++) {
			        		  //发生故障的时刻应该位于开机与关机记录时间之间
				        	  boolean before = records.get(j).getRecordStartDate().before(fd);
				        	  boolean after = records.get(j).getRecordEndDate().after(fd);
				        	  if(records.get(j).getRadarId().getRadarName().equals(RName)&&records.get(j).getWithFault()==1
				        			&&before&&after	  	) {
				        		  fault.setRecordId(records.get(j));
				        	  }
				          }
		        		  
		        	  }
		        	if(faultTypes!=null||faultTypes.size()>0) {
		        		 for(int k =0;k<faultTypes.size();k++) {
		 		        	if(faultType.equals(faultTypes.get(k).getFaultName())) {
		 		        		fault.setFaultTypeId(faultTypes.get(k));;
		 		        	}
		 		        }
		        	}
		        	  
		       
		        fault.setFaultLocation(faultLocation);
		        fault.setFaultReason(faultReason);
		        flagg = faultRecordServiceImpl.add(fault);
		        
		          }
		          if(flagg) {
		                JOptionPane.showMessageDialog(null, "故障记录成功导入");

			        	System.out.println("故障记录成功导入");
			        }
		          
		        }
			}else if(dateType.equals("监测数据")) {
				fileChooser = new JFileChooser();             
		        //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
		        fileChooser.setFileFilter(filter);      
		        int returnValue = fileChooser.showOpenDialog(null);    
		        //弹出一个文件选择提示框
		        if (returnValue == fileChooser.APPROVE_OPTION) {
		        //当用户选择文件后获取文件路径
		        chooseFile = fileChooser.getSelectedFile();
		        
		        //根据文件路径初始化Excel工作簿
		        Workbook workBook=null;
		         try {
		                 workBook = Workbook.getWorkbook(chooseFile);
		         } catch (Exception event) {	
		        	 event.printStackTrace();
		 
		 
		         } 
		          //获取该工作表中的第一个工作表   
		          Sheet sheet=workBook.getSheet(0);  
		          //获取该工作表的行数，以供下面循环使用   
		          int rowSize=sheet.getRows();  
		          System.out.println("监测数据行数:"+rowSize);
		          SpringUtil s = new SpringUtil();
	    		  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
	    		  List<Radar> r = radarServiceImpl.getAllRadars();
	    		  BasicInfoServiceImpl basicInfoServiceImpl = (BasicInfoServiceImpl) s.getBean("BasicInfoServiceImpl"); 
		          List<com.radar.Entity.BasicInfo> basicInfo = basicInfoServiceImpl.getAllBasicInfo();
		          DynamicDataServiceImpl dynamicDataServiceImpl = (DynamicDataServiceImpl) s.getBean("DynamicDataServiceImpl"); 

		          for(int i=1;i<rowSize;i++) {
	    			  DynamicData dynamicData = new DynamicData();
		        	  String RName = sheet.getCell(0,i).getContents().toString();
		        	  String paraName = sheet.getCell(1,i).getContents().toString();
		        	  String paraValue = sheet.getCell(2,i).getContents().toString();
		        	  Cell collectDate = sheet.getCell(3, i);
		        	 

		        	  java.util.Date cd = null;

		        	  if(collectDate.getType()==CellType.DATE) {
		        		  DateCell dcs = (DateCell) collectDate;
		                     java.util.Date cDate = dcs.getDate();
		                     TimeZone gmt = TimeZone.getTimeZone("GMT");

		                     SimpleDateFormat sdf = new SimpleDateFormat(
		                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		                     sdf.setTimeZone(gmt);


			                String cTime = sdf.format(cDate);
			                TimeZone local = TimeZone.getDefault();
			                sdf.setTimeZone(local);
				        	  try {
				        		  cd = sdf.parse(cTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  dynamicData.setCollectDate(cd);
		        	  }
		        	  if(basicInfo!=null||basicInfo.size()>0) {
		        		  for(int j=0;j<basicInfo.size();j++) {
				        	  if(paraName.equals(basicInfo.get(j).getParamName())) {
				        		  dynamicData.setParamId(basicInfo.get(j));
				        	  }

			        	  }
			        	 
		        	  }
		        	  if(r!=null||r.size()>0) {
		        		  for(int k= 0;k<r.size();k++) {
			        		  if(RName.equals(r.get(k).getRadarName())) {
			        			  dynamicData.setRadarId(r.get(k));
			        		  }
			        	  }
		        	  }
		        	 
		        	  dynamicData.setDataVaule(paraValue);
		        	  flagj = dynamicDataServiceImpl.add(dynamicData);
		          } 
		          if(flagj) {
		                JOptionPane.showMessageDialog(null, "监测数据成功导入");

			        	System.out.println("监测数据成功导入");
			        }
		        }
			}
			}
		});
		//按选择的雷达编号导出开机记录、
			OutButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//部队和始末时间都为空，雷达编号和数据类型不为空
				String dateType=(String)TypeName.getSelectedItem();
				String radarNumber=(String)RadarName.getSelectedItem();
				String managerNumber=(String)ManagerName.getSelectedItem();
				String startTimeString = SaveStartTime.getText();
				String endTimeString = SaveEndTime.getText();
				SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date startTimeDate1 =null;
				String startTimeDate="";
				if(!startTimeString.equals("")) {
				try {
					startTimeDate1=new Date(sdf5.parse(startTimeString).getTime());
					startTimeDate =sdf6.format(startTimeDate1);
//					startTimeDate = sdf5.parse(startTimeString);
				} catch (ParseException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
				}
				Date endTimeDate1=null;
				String endTimeDate="";
				if(!endTimeString.equals("")) {
				try {
//					endTimeDate = sdf5.parse(endTimeString);
					endTimeDate1=new Date(sdf5.parse(endTimeString).getTime());
					endTimeDate =sdf5.format(endTimeDate1);
				} catch (ParseException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
				}
				//没有选择导出数据的类型，起始时间时，提示框；部队类型默认为all，雷达编号默认为all
				if(((dateType.equals("")||dateType.equals(null))&&(radarNumber.equals("All"))
						&&startTimeString.equals("")&&endTimeString.equals(""))&&managerNumber.equals("All")){
					JOptionPane.showMessageDialog(null, "请选择数据类型/起始时间", "标题",JOptionPane.WARNING_MESSAGE);  

				}
				//导出所有部队下面的所有雷达开机记录
				else if(dateType.equals("开机记录")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath +"所有雷达的开机记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("开机记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//直接查询record的全部数据	
						 SpringUtil s = new SpringUtil();
			    	
			    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
			    		 List<Record> record =recordServiceImpl.selectRecordByTime(startTimeDate,endTimeDate);
			    		
			    		 
						String jugDefault = "";
						if(record!=null||record.size()>0) {
							int k =1 ;//从第er行开始写入数据

							for (int i = 0; i < record.size(); i++) {	
								if(record.get(i).getWithFault()==0) {
									jugDefault="否";
								}else if(record.get(i).getWithFault()==1){
									jugDefault="是";
								}
							    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							   java.util.Date startDate =record.get(i).getRecordStartDate();
							   java.util.Date endDate =  record.get(i).getRecordEndDate();
								ws.addCell(new Label(0, k, record.get(i).getRadarId().getRadarName(), wcf2));
								ws.addCell(new Label(1, k, sdf1.format(startDate),wcf2));
								ws.addCell(new Label(2, k, sdf1.format(endDate),wcf2));
								ws.addCell(new Label(3, k, record.get(i).getActivityId().getActivityName(),wcf2));
								ws.addCell(new Label(4, k,jugDefault,wcf2));
								//ws.mergeCells(4, 5, 5, 5);//合并两列，按参数顺序，意思是第4列的第五行，跟第五列的第五行合并为一个单元格			
								k++;		
							}
						}
							
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}	
			
			
					
				}
				//导出某台雷达在某段时间内的开机记录
				else if(dateType.equals("开机记录")&&(!radarNumber.equals("All"))
				&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals(""))
				
				{
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath + radarNumber+"开机记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("开机记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询出来的数据,这个方法是演示所用	
						 SpringUtil s = new SpringUtil();
			    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		 List<Radar> r = radarServiceImpl.getAllRadars();
			    		 Integer	RecordRadarId = null;
			    		 if(r!=null||r.size()>0) {
			    			 for(int i=0;i<r.size();i++) {
				    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
				    			 RecordRadarId = r.get(i).getRadarId();
				    			 }
				    			
				    		 }
			    		 }
			    		 
			    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
			    		 List<Record> record =recordServiceImpl.selectRecordByRadar(RecordRadarId,startTimeDate,endTimeDate);
			    		
			    		 
						String jugDefault = "";
						if(record!=null||record.size()>0) {
							int k =1 ;//从第er行开始写入数据

							for (int i = 0; i < record.size(); i++) {	
								if(record.get(i).getWithFault()==0) {
									jugDefault="否";
								}else if(record.get(i).getWithFault()==1){
									jugDefault="是";
								}
							    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							   java.util.Date startDate =record.get(i).getRecordStartDate();
							   java.util.Date endDate =  record.get(i).getRecordEndDate();
								ws.addCell(new Label(0, k, radarNumber, wcf2));
								ws.addCell(new Label(1, k, sdf1.format(startDate),wcf2));
								ws.addCell(new Label(2, k, sdf1.format(endDate),wcf2));
								ws.addCell(new Label(3, k, record.get(i).getActivityId().getActivityName(),wcf2));
								ws.addCell(new Label(4, k,jugDefault,wcf2));
								//ws.mergeCells(4, 5, 5, 5);//合并两列，按参数顺序，意思是第4列的第五行，跟第五列的第五行合并为一个单元格			
								k++;		
							}
						}
							
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}	
			
			
				//导出某个部队开机记录
				}else if(dateType.equals("开机记录")&&(radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath + managerNumber+"开机记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("开机记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询出来的数据,这个方法是演示所用	
						 SpringUtil s = new SpringUtil();
						 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
						 List<Manager> managers =managerServiceImpl.getAllManager();
			    		 Integer	managerIdInRadar = null ;

						 if(managers!=null||managers.size()>0) {

							 for(int i=0;i<managers.size();i++) {
								 if(managers.get(i).getManagerName().equals(managerNumber)) {
									 managerIdInRadar=managers.get(i).getManagerId();
								 }
							 }
						 }
			    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		 List<Radar> r = radarServiceImpl.getRadarsByManagerId(managerIdInRadar);
			    		 List<List<Record>> recordsList= new ArrayList<List<Record>>();
			    		 List<Record> record = null ;
			    		 
			    			
				    		 Integer	RecordRadarId ;

			    			 for(int i=0;i<r.size();i++) {
			    				 RecordRadarId=r.get(i).getRadarId();
			    				 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
					    		 record =recordServiceImpl.slectRecordByManager(RecordRadarId,startTimeDate,endTimeDate);
					    		 if(record!=null||record.size()>0) {
						    		 recordsList.add(record);

					    		 }
				    			 }
				    			
				    		
			    		 
			    		 
			    		
			    		 
						String jugDefault = "";
						if(recordsList!=null||recordsList.size()>0) {
							for(int i=0;i<recordsList.size();i++) {
								if(recordsList.get(i)!=null||recordsList.get(i).size()>0) {
									int k =1 ;//从第er行开始写入数据
									for(int j=0;j<recordsList.get(i).size();j++) {
									if(recordsList.get(i).get(j).getWithFault()==0) {
										jugDefault="否";

									}else if(recordsList.get(i).get(j).getWithFault()==1) {
										jugDefault="是";

									}
									  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									   java.util.Date startDate =recordsList.get(i).get(j).getRecordStartDate();
									   java.util.Date endDate =recordsList.get(i).get(j).getRecordEndDate();
									   ws.addCell(new Label(0, k, recordsList.get(i).get(j).getRadarId().getRadarName(), wcf2));
										ws.addCell(new Label(1, k, sdf1.format(startDate),wcf2));
										ws.addCell(new Label(2, k, sdf1.format(endDate),wcf2));
										ws.addCell(new Label(3, k, recordsList.get(i).get(j).getActivityId().getActivityName(),wcf2));
										ws.addCell(new Label(4, k,jugDefault,wcf2));
										//ws.mergeCells(4, 5, 5, 5);//合并两列，按参数顺序，意思是第4列的第五行，跟第五列的第五行合并为一个单元格			
										k++;		

								}

								}
							}
						}
						
							
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}	
				}
				//导出所有部队下面所有雷达的故障记录
				else if(dateType.equals("故障记录")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath +"所有部队雷达故障记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("故障记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询全部故障记录	
						 SpringUtil s = new SpringUtil();
			    		 FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
			    		 List<Fault> fault = faultRecordServiceImpl.selectFaultRecordByTime(startTimeDate,endTimeDate);
			    		 if(fault!=null||fault.size()>0) {
	    					 int k=1;
	    					 for(int n=0;n<fault.size();n++) {
	    						 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								    Date fTime = fault.get(n).getFaultDate();
									ws.addCell(new Label(0, k, fault.get(n).getRecordId().getRadarId().getRadarName(), wcf2));
									ws.addCell(new Label(1, k, fault.get(n).getFaultTypeId().getFaultName(),wcf2));
									ws.addCell(new Label(2, k, sdf2.format(fTime),wcf2));
									ws.addCell(new Label(3, k, fault.get(n).getFaultLocation(),wcf2));
									ws.addCell(new Label(4, k,fault.get(n).getFaultLocation(),wcf2));
									k++;
	    					 }
	    				 }
			    		
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}
				}
				//导出某个部队的故障记录
				else if(dateType.equals("故障记录")&&(radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath +managerNumber+ "故障记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("故障记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询某个部队的故障记录	
						 SpringUtil s = new SpringUtil();
						 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
			    		 List<Manager> m = managerServiceImpl.getAllManager();
			    		Integer managerId = null;
			    		 if(m!=null||m.size()>0) {
			    			 for(int i=0;i<m.size();i++) {
			    				 if(m.get(i).getManagerName().equals(managerNumber)) {
			    					managerId = m.get(i).getManagerId();
			    				 }
			    			 }
			    		 }
			    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		 List<Radar> r = radarServiceImpl.getRadarsByManagerId(managerId);
			    		 List<Record> record =null;
			    		 List <Fault>fault=null;
			    		 if(r!=null||r.size()>0) {
			    			 for(int i=0;i<r.size();i++) {
					    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");

					    		 record=recordServiceImpl.selectRecordByRadarId(r.get(i).getRadarId()); 
			    			 if(record!=null||record.size()>0) {
			    				 for(int j=0;j<record.size();j++) {
			    					 FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
					    			 fault = faultRecordServiceImpl.selectFaultRecordByRecordId(record.get(j).getRecordId(),startTimeDate,endTimeDate);
			    				 if(fault!=null||fault.size()>0) {
			    					 int k=1;
			    					 for(int n=0;n<fault.size();n++) {
			    						 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										    Date fTime = fault.get(n).getFaultDate();
											ws.addCell(new Label(0, k, fault.get(n).getRecordId().getRadarId().getRadarName(), wcf2));
											ws.addCell(new Label(1, k, fault.get(n).getFaultTypeId().getFaultName(),wcf2));
											ws.addCell(new Label(2, k, sdf2.format(fTime),wcf2));
											ws.addCell(new Label(3, k, fault.get(n).getFaultLocation(),wcf2));
											ws.addCell(new Label(4, k,fault.get(n).getFaultLocation(),wcf2));
											k++;
			    					 }
			    				 }
			    				 }
			    			 }
			    			 }
			    		 }
			    		
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}
				}
				//导出某台雷达的故障记录
				else if(dateType.equals("故障记录")&&(!radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath +radarNumber+ "故障记录" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("故障记录", 0);
						for(int i =0;i<5;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询出来的数据,这个方法是演示所用	
						 SpringUtil s = new SpringUtil();
			    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		 List<Radar> r = radarServiceImpl.getAllRadars();
			    		 Integer	RecordRadarId = null;
			    		 if(r!=null||r.size()>0) {
			    			 for(int i=0;i<r.size();i++) {
				    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
				    			 RecordRadarId = r.get(i).getRadarId();
				    			 }
				    			
				    		 }
			    		 }
			    		 
			    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
			    		 List<Record> record =recordServiceImpl.selectRecordByRadarId(RecordRadarId);
			    		 List <List<Fault>> faults = new ArrayList<List<Fault>>();
			    		 List<Fault> fault=null;
			    		if(record!=null||record.size()>0) {
			    			 for(int j =0;j<record.size();j++) {
					    			FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
					    			 fault = faultRecordServiceImpl.selectFaultRecordByRecordId(record.get(j).getRecordId(),startTimeDate,endTimeDate);
					    			if(fault!=null||fault.size()>0) {
						    			 faults.add(fault);

					    			}
					    		}
			    		}
			    		
			    		
			    		
			    		 
						if(faults!=null||faults.size()>0) {

							for(int i= 0;i<faults.size();i++) {
								if(faults.get(i).size()>0) {
								int k =1 ;//从第er行开始写入数据
								for(int j=0;j<faults.get(i).size();j++) {
									 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									    Date fTime = faults.get(i).get(j).getFaultDate();
										ws.addCell(new Label(0, k, radarNumber, wcf2));
										ws.addCell(new Label(1, k, faults.get(i).get(j).getFaultTypeId().getFaultName(),wcf2));
										ws.addCell(new Label(2, k, sdf2.format(fTime),wcf2));
										ws.addCell(new Label(3, k, faults.get(i).get(j).getFaultLocation(),wcf2));
										ws.addCell(new Label(4, k,faults.get(i).get(j).getFaultReason(),wcf2));
										k++;

								}
							   
								}
							}	
						}
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}
				}
				//导出所有部队下所有雷达的监测数据
				else if(dateType.equals("监测数据")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath +"全部雷达监测数据" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("监测数据", 0);
						for(int i =0;i<4;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","参数名", "参数值","采集时间"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询所有监测数据	
								SpringUtil s = new SpringUtil();

			    				 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
					    		 List<DynamicData>  dynamicData = dynamicDataServiceImpl.selectDynamicDataByTime(startTimeDate,endTimeDate);
			    			if(dynamicData!=null||dynamicData.size()>0) {
			    				int k=1;
			    				for(int j=0;j<dynamicData.size();j++) {
			    					 SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									    Date cTime = dynamicData.get(j).getCollectDate();
										ws.addCell(new Label(0, k,dynamicData.get(j).getRadarId().getRadarName() , wcf2));
										ws.addCell(new Label(1, k, dynamicData.get(j).getParamId().getParamName(),wcf2));
										ws.addCell(new Label(2, k, dynamicData.get(j).getDataVaule(),wcf2));
										ws.addCell(new Label(3, k, sdf3.format(cTime),wcf2));
								
							   
								k++;
			    				}
			    			}
			    			 
			    		 
					
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}
				}
					//导出某个部队的监测数据
					else if(dateType.equals("监测数据")&&(radarNumber.equals("All"))
							&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
						//这里为导出文件存放的路径	
						String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
						//加入一个uuid随机数是因为	
						//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
						File file = new File(filePath);	
						if (!file.exists()) {		
							file.mkdirs();	
						}
						  Calendar calendar = Calendar.getInstance();
					        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					        String str3 = simpleDateFormat.format(calendar.getTime());
					
						// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
						String filePath2 = filePath + managerNumber+"监测数据" + "_"  + str3 + ".xls";	
						WritableWorkbook wb = null;	
						try {		
							File file2 = new File(filePath2);		
							if (!file2.exists()) {//不存在，创建			
								file2.createNewFile();		
							}		
							wb = Workbook.createWorkbook(file2);//创建xls表格文件
							
							// 表头显示
							WritableCellFormat wcf = new WritableCellFormat();		
							wcf.setAlignment(Alignment.CENTRE);// 水平居中		
							wcf.setWrap(true);		
							wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
							wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
							wcf.setBackground(jxl.format.Colour.PERIWINKLE);
							// 内容显示		
							WritableCellFormat wcf2 = new WritableCellFormat();	
							
							wcf2.setWrap(true);//设置单元格可以换行		
							wcf2.setAlignment(Alignment.CENTRE);//水平居中		
							wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
							wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
					 
							//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
							WritableSheet ws = wb.createSheet("监测数据", 0);
							for(int i =0;i<4;i++) {
								ws.setColumnView(i, 30);

							};
							//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
							//代表着表格中第一列的第一行显示查询结果几个字
//							ws.addCell(new Label(0,0, "导出结果"));
							// 导出时生成表头
							String[] TestToXls = { "雷达编号","参数名", "参数值","采集时间"};
							for (int i = 0; i < TestToXls.length; i++) {
								//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
								ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
							}
									
							//查询某个部队的监测数据	
							 SpringUtil s = new SpringUtil();
							 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
				    		 List<Manager> m = managerServiceImpl.getAllManager();
				    		 Integer managerId=null;
				    		 if(m!=null||m.size()>0) {
				    			 for(int i=0;i<m.size();i++) {
				    				 if(m.get(i).getManagerName().equals(managerNumber)) {
				    					 managerId=m.get(i).getManagerId();
				    				 }
				    			 }
				    		 }
				    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
				    		 List<Radar> radar =radarServiceImpl.getRadarsByManagerId(managerId);
				    		 if(radar!=null||radar.size()>0) {
				    			 for(int i=0;i<radar.size();i++) {
				    				 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
						    		 List<DynamicData>  dynamicData = dynamicDataServiceImpl.selectDynamicDataByRadarId(radar.get(i).getRadarId(),startTimeDate,endTimeDate);
				    			if(dynamicData!=null||dynamicData.size()>0) {
				    				int k=1;
				    				for(int j=0;j<dynamicData.size();j++) {
				    					 SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										    Date cTime = dynamicData.get(j).getCollectDate();
											ws.addCell(new Label(0, k,dynamicData.get(j).getRadarId().getRadarName() , wcf2));
											ws.addCell(new Label(1, k, dynamicData.get(j).getParamId().getParamName(),wcf2));
											ws.addCell(new Label(2, k, dynamicData.get(j).getDataVaule(),wcf2));
											ws.addCell(new Label(3, k, sdf3.format(cTime),wcf2));
									
								   
									k++;
				    				}
				    			}
				    			 }
				    		 }
						
							wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
			                JOptionPane.showMessageDialog(null, "导出成功");
						} catch (IOException e1) {
							e1.printStackTrace();

			                JOptionPane.showMessageDialog(null, "导出失败");
						} catch (JxlWriteException e2) {
							e2.printStackTrace();	

			                JOptionPane.showMessageDialog(null, "导出失败");
						} catch (WriteException e3) {
							e3.printStackTrace();	

			                JOptionPane.showMessageDialog(null, "导出失败");
						} finally {		
							try {			
								if (wb != null) {
									wb.close();
								}		
							} catch (WriteException e4) {
								e4.printStackTrace();
							} catch (IOException e5) {
								e5.printStackTrace();
							}	
						}
					}
					//导出某台雷达的监测数据
				else if(dateType.equals("监测数据")&&(!radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					//这里为导出文件存放的路径	
					String filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
					//加入一个uuid随机数是因为	
					//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
					File file = new File(filePath);	
					if (!file.exists()) {		
						file.mkdirs();	
					}
					  Calendar calendar = Calendar.getInstance();
				        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String str3 = simpleDateFormat.format(calendar.getTime());
				
					// 给要导出的文件起名为 "测试导出数据表_时间.xls"	
					String filePath2 = filePath + radarNumber+"监测数据" + "_"  + str3 + ".xls";	
					WritableWorkbook wb = null;	
					try {		
						File file2 = new File(filePath2);		
						if (!file2.exists()) {//不存在，创建			
							file2.createNewFile();		
						}		
						wb = Workbook.createWorkbook(file2);//创建xls表格文件
						
						// 表头显示
						WritableCellFormat wcf = new WritableCellFormat();		
						wcf.setAlignment(Alignment.CENTRE);// 水平居中		
						wcf.setWrap(true);		
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号		
						wcf.setBackground(jxl.format.Colour.PERIWINKLE);
						// 内容显示		
						WritableCellFormat wcf2 = new WritableCellFormat();	
						
						wcf2.setWrap(true);//设置单元格可以换行		
						wcf2.setAlignment(Alignment.CENTRE);//水平居中		
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中		
						wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号
				 
						//导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
						WritableSheet ws = wb.createSheet("监测数据", 0);
						for(int i =0;i<4;i++) {
							ws.setColumnView(i, 30);

						};
						//WritableSheet ws2 = wb.createSheet("sheet2", 1);//第2个sheet页		
						//代表着表格中第一列的第一行显示查询结果几个字
//						ws.addCell(new Label(0,0, "导出结果"));
						// 导出时生成表头
						String[] TestToXls = { "雷达编号","参数名", "参数值","采集时间"};
						for (int i = 0; i < TestToXls.length; i++) {
							//i,代表的第几列，1，代表第2行，第三个参数为要显示的内容，第四个参数，为内容格式设置（按照wcf的格式显示）			
							ws.addCell(new Label(i, 0, TestToXls[i],wcf));//在sheet1中循环加入表头
						}
								
						//查询出来的数据,这个方法是演示所用	
						 SpringUtil s = new SpringUtil();
			    		 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
			    		 List<Radar> r = radarServiceImpl.getAllRadars();
			    		 Integer	dynamicDataRadarId = null;
			    		 if(r!=null||r.size()>0) {
			    			 for(int i=0;i<r.size();i++) {
				    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
				    			 dynamicDataRadarId = r.get(i).getRadarId();
				    			 }
				    			
				    		 }
			    		 }
			    		 
			    		 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
			    		 List<DynamicData>  dynamicData = dynamicDataServiceImpl.selectDynamicDataByRadarId(dynamicDataRadarId,startTimeDate,endTimeDate);
			    
			    		 
						//从第er行开始写入数据
						if(dynamicData!=null||dynamicData.size()>0) {
							int k =1 ;
							for(int i= 0;i<dynamicData.size();i++) {
								 SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								    Date cTime = dynamicData.get(i).getCollectDate();
									ws.addCell(new Label(0, k, radarNumber, wcf2));
									ws.addCell(new Label(1, k, dynamicData.get(i).getParamId().getParamName(),wcf2));
									ws.addCell(new Label(2, k, dynamicData.get(i).getDataVaule(),wcf2));
									ws.addCell(new Label(3, k, sdf3.format(cTime),wcf2));
							
						   
							k++;
						}
						}
					
						wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
		                JOptionPane.showMessageDialog(null, "导出成功");
					} catch (IOException e1) {
						e1.printStackTrace();

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (JxlWriteException e2) {
						e2.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} catch (WriteException e3) {
						e3.printStackTrace();	

		                JOptionPane.showMessageDialog(null, "导出失败");
					} finally {		
						try {			
							if (wb != null) {
								wb.close();
							}		
						} catch (WriteException e4) {
							e4.printStackTrace();
						} catch (IOException e5) {
							e5.printStackTrace();
						}	
					}
				}
			}
		});
			
			//部队下拉框事件（更新雷达下拉框数据）
			ManagerName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						RadarName.initData(ManagerName.getSelectedItem().toString());
					}
					catch(Exception execption) {
						execption.printStackTrace();
					}
				}
			});
			//雷达下拉框事件（更新部队下拉框数据）
			RadarName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ManagerName.initData(RadarName.getSelectedItem().toString());
					}
					catch(Exception execption) {
						execption.printStackTrace();
					}
				}
			});
	}







	private void initUI() {
		// TODO Auto-generated method stub
		//顶部标题、分割线
		setLabelForTitle();
		panel.add(LabelForTitle);
		panel.add(separator);
		
		//雷达所属部队标签及下拉框
		setRadarManager();
		panel.add(RadarManager);
		panel.add(ManagerName);
		//雷达编号及下拉框
		setRadarNumber();
		panel.add(RadarNumber);
		panel.add(RadarName);
		
		//数据类型
		setDataType();
		panel.add(DataType);
		panel.add(TypeName);
		//起始时间
		setStartTime();
		CalendarPanel p = new CalendarPanel(SaveStartTime, "yyyy-MM-dd");
		p.initCalendarPanel();
		panel.add(StartTime);
		panel.add(SaveStartTime);
		panel.add(p);
		
		//终止时间
		setEndTime();
		CalendarPanel e = new CalendarPanel(SaveEndTime, "yyyy-MM-dd");
		e.initCalendarPanelEndTime();
		panel.add(EndTime);
		panel.add(SaveEndTime);
		panel.add(e);
		panel.setComponentZOrder(e, 0);
		//导入/导出按钮
		setInAndOutButton();
		panel.add(InButton);
		panel.add(OutButton);
		
		 
	}






	private void setInAndOutButton() {
		// TODO Auto-generated method stub
		InButton = new BottomButtonForTable("导入",223, 420,70,28,12);
		OutButton = new BottomButtonForTable("导出",315, 420,70,28,12);
		
	}







	private void setEndTime() {
		// TODO Auto-generated method stub
		EndTime = new JLabel("终止时间：");
		EndTime.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		EndTime.setBounds(20, 348, 80, 22);
		
		SaveEndTime = new JTextField();
		SaveEndTime.setBounds(133, 346, 157, 25);
		SaveEndTime.setColumns(10);
	}







	private void setStartTime() {
		// TODO Auto-generated method stub
		StartTime = new JLabel("起始时间：");
		StartTime.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		StartTime.setBounds(20, 283, 80, 22);
		
		SaveStartTime = new JTextField();
		SaveStartTime.setBounds(133, 281, 157, 25);
		SaveStartTime.setColumns(10);
	}







	private void setDataType() {
		// TODO Auto-generated method stub
		DataType = new JLabel("数据类型");
		DataType.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		DataType.setBounds(20, 218, 80, 22);
		TypeName = new JComboBox();
		TypeName.setBounds(133, 218, 157, 25);
		TypeName.addItem("");
		TypeName.addItem("开机记录");
		TypeName.addItem("故障记录");
		TypeName.addItem("监测数据");
//		TypeName = new	JComBoxForDataType(133,218);
//		TypeName.setBounds(133, 218, 157, 25);
	}







	private void setRadarNumber() {
		// TODO Auto-generated method stub
		RadarNumber = new JLabel("雷达编号：");
		RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		RadarNumber.setBounds(20, 153, 80, 22);
		RadarName = new	JComBoxForRadarNumber(133,151);
//		RadarName.setBounds(133, 151, 157, 25);

	}







	private void setRadarManager() {
		// TODO Auto-generated method stub
		RadarManager = new JLabel("雷达所属部队：");
		RadarManager.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		RadarManager.setBounds(20, 88, 112, 22);
		ManagerName = new JComoBoxForManager(133,86);
		ManagerName.setBounds(133, 86, 157, 25);

		
	}







	private void setLabelForTitle() {
		// TODO Auto-generated method stub
		LabelForTitle =  new JLabel("数据代入/导出");
		LabelForTitle.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		LabelForTitle.setBounds(20, 16, 137, 27);
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);
	}
}
