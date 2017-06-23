package ttadm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttadm.model.*;
import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadNomenclGroup;
import tt.modelattribute.MA_loadNomenclGroupRoot;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;


@Service
public class ReadExcelFile {
	
	//@Autowired
	//private static ProcessingFiles processFiles;
		
    private static Workbook getWorkbook(File tmpFile) throws IOException {
        
    	Workbook workbook = null;
        FileInputStream fis = new FileInputStream(tmpFile);
        
        if (tmpFile.toString().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (tmpFile.toString().endsWith("xls")) {
            workbook = new HSSFWorkbook(fis);
        }
        return workbook;
    }
	
    public static Cell setCellTypeToString(Cell cell) {
    	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    	return cell;
    }
	
	public  static List<?> processFile(File tmpFile, DirProvider dirProvider, MA_loadProvider mA_loadProvider) throws IOException {
		
		List<DirProvider>  lProvs = new ArrayList<DirProvider>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
        //FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadProvider.getRow()) {
        			try {
		        		dirProvider = new DirProvider();
		        		
		        	
		        		dirProvider.setName(df.formatCellValue( setCellTypeToString(tmp.getCell(mA_loadProvider.getCol_name()-1)) ));
		        		//HSSFCell cell = (HSSFCell) tmp.getCell(mA_loadProvider.getCol_code()-1);
		        		//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        		long code = Long.parseLong(df.formatCellValue( setCellTypeToString( tmp.getCell(mA_loadProvider.getCol_code()-1) ) ) );
		        		dirProvider.setCode(code );
		        	
			        	lProvs.add(dirProvider);
        			} catch (java.lang.NumberFormatException nfe) {
						//nfe.printStackTrace();
						throw new java.lang.NumberFormatException("("+(row_+1) +") Ошибка формата данных !");
						
		    		}

        		}
        	//System.out.println("row - "+row_);
        	
        	++row_;
        }
        
		return lProvs;
		
	}

	
	public  static List<?> processFile(File tmpFile, DirNomenclature dirNomenclature, MA_loadNomencl mA_loadNomencl, HashMap<Long,DirNomenclGroup> hmNomenclGroup, HashMap<String,DirGender> hmDGen,
											HashMap<Long,DirProvider> hmDProv) throws Exception{
		
		List<DirNomenclature>  lNomencls = new ArrayList<DirNomenclature>();
		
		Timestamp access_date = new Timestamp(new Date().getTime());
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
        //HashMap<Long,String> hmPollPaths = new HashMap<Long,String>(); //Список файлов на загрузку
        HashMap<Long,List<String>> hmPollPaths = new HashMap<Long,List<String>>(); //Список файлов на загрузку
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadNomencl.getRow()-1) {
        			dirNomenclature = new DirNomenclature();
	        	
        			dirNomenclature.setName(df.formatCellValue( tmp.getCell(mA_loadNomencl.getCol_name()-1)).trim());
        			dirNomenclature.setModel(df.formatCellValue( tmp.getCell(mA_loadNomencl.getCol_model()-1)).trim());
        			dirNomenclature.setCode(Long.parseLong(df.formatCellValue( setCellTypeToString(tmp.getCell(mA_loadNomencl.getCol_code()-1))).trim() ) );
        			dirNomenclature.setArticle(df.formatCellValue( tmp.getCell(mA_loadNomencl.getCol_article()-1)).trim());
        			
        			String composition = df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_composition()-1)).trim().length() == 0? null: df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_composition()-1)).trim();
        			dirNomenclature.setComposition(composition);

        			dirNomenclature.setDirNomenclGroup(hmNomenclGroup.get(Long.parseLong(df.formatCellValue( tmp.getCell(mA_loadNomencl.getCol_codeNomenclGroup()-1)).trim() ) ) );
        			dirNomenclature.setDirGender(hmDGen.get(df.formatCellValue( tmp.getCell(mA_loadNomencl.getCol_gender()-1)).toLowerCase().trim() ) );
        			dirNomenclature.setDirProvider(hmDProv.get(new Long((df.formatCellValue( setCellTypeToString(tmp.getCell((mA_loadNomencl.getCol_codeProvider()-1))))))));
        			dirNomenclature.setAccess_date(access_date);
        			

        			String path = df.formatCellValue(tmp.getCell(mA_loadNomencl.getCol_pathToImage()-1)).trim();
        			//System.out.println(path);


        			if(path.length() >0)
        			{
        				
        				StringTokenizer st = new StringTokenizer(path,";");
        				List<String> files = new ArrayList<String>();
        				while (st.hasMoreTokens()) {
        					
        					String file = st.nextToken();
        					//System.out.println("file - "+file);
        					files.add(Constants.IMAGES_SERVER +File.separator+file.substring(3).replace('\\', '/'));
        				}
        				
        				hmPollPaths.put(dirNomenclature.getCode(),files);
        				//System.out.println(files);
        			}
        			
        			
        			
		        	lNomencls.add(dirNomenclature);
        		}
        	
        	
        	++row_;
        }
        //System.out.println(hmPollPaths);
        //MainAutoLoad.startPhotoFileService2(hmPollPaths);
        
        
		return lNomencls;
		
	}

	public  static List<?> processFile(File tmpFile, DirNomenclGroup dirNomenclGroup, MA_loadNomenclGroup mA_loadNomenclGroup, HashMap<Long,DirNomenclGroupRoot> hmNomenclGroupRoot) throws Exception{
		
		List<DirNomenclGroup>  lNomencls = new ArrayList<DirNomenclGroup>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadNomenclGroup.getRow()-1) {
        			dirNomenclGroup = new DirNomenclGroup();
	        	
        			dirNomenclGroup.setName(df.formatCellValue(tmp.getCell(mA_loadNomenclGroup.getCol_name()-1)));
        			dirNomenclGroup.setCode(Long.parseLong(df.formatCellValue( setCellTypeToString(tmp.getCell(mA_loadNomenclGroup.getCol_code()-1))) ) );
        			dirNomenclGroup.setDirNomenclGroupRoot(hmNomenclGroupRoot.get(Long.parseLong(df.formatCellValue(tmp.getCell(mA_loadNomenclGroup.getCol_codeNomenclGroupRoot()-1)) )) );
        			
		        	lNomencls.add(dirNomenclGroup);
        		}
        	
        	
        	++row_;
        }
        
		return lNomencls;
		
	}


	public static Collection<?> processFile(File tmpFile, Tail tail, MA_loadTail mA_loadTail,  HashMap<Long,DirNomenclature> hmNomencl)  throws Exception {
		// TODO Auto-generated method stub
		List<Tail>  lTails = new ArrayList<Tail>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
        
        
        Timestamp timestamp = new Timestamp(new Date().getTime());
		
		
		int row_ = 0;
		int index = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        		if(row_ >= mA_loadTail.getRow()-1) {
        			tail = new Tail();
        			tail.setIndex(index++);
        			tail.setAmountTail(Integer.parseInt(df.formatCellValue(tmp.getCell(mA_loadTail.getCol_amountTail()-1)) ));
        			tail.setFirstPrice(NumberFormat.getNumberInstance().parse(df.formatCellValue(tmp.getCell(mA_loadTail.getCol_firstPrice()-1) )).doubleValue());
        			tail.setCreate_date(timestamp);
        			tail.setDirNomenclature( hmNomencl.get(new Long((df.formatCellValue(tmp.getCell((mA_loadTail.getCol_codeNomencl()-1)))))) );
        			tail.setSize(df.formatCellValue(tmp.getCell(mA_loadTail.getCol_size()-1)).replaceAll("р-р", "").trim());
        			tail.setNds( new Integer(df.formatCellValue(tmp.getCell(mA_loadTail.getNds()-1))) );
        			tail.setNadb_opt( new Integer(df.formatCellValue(tmp.getCell(mA_loadTail.getNadb_opt()-1))) );
        			tail.setNadb_rozn( new Integer(df.formatCellValue(tmp.getCell(mA_loadTail.getNadb_rozn()-1))) );
        			
        			lTails.add(tail);
        		}
        	
        	
        	++row_;
        }
        
		return lTails;
	}



	public static Collection<?> processFile(File tmpFile, DirNomenclGroupRoot dirNomenclGroupRoot, MA_loadNomenclGroupRoot mA_loadNomenclGroupRoot) throws Exception {
		List<DirNomenclGroupRoot>  lNomencls = new ArrayList<DirNomenclGroupRoot>();
		
		Workbook workbook = getWorkbook(tmpFile);
        Sheet firstSheet = workbook.getSheetAt(0);  
        Iterator<Row> rowIterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter();
		
		
		int row_ = 0;
        while(rowIterator.hasNext() )
        {
        	Row tmp = rowIterator.next();
        	
        	
        		if(row_ >= mA_loadNomenclGroupRoot.getRow()-1) {
        			dirNomenclGroupRoot = new DirNomenclGroupRoot();
	        	
        			dirNomenclGroupRoot.setName(df.formatCellValue(tmp.getCell(mA_loadNomenclGroupRoot.getCol_name()-1)));
        			dirNomenclGroupRoot.setCode(Long.parseLong(df.formatCellValue( setCellTypeToString(tmp.getCell(mA_loadNomenclGroupRoot.getCol_code()-1))) ) );
        			
		        	lNomencls.add(dirNomenclGroupRoot);
        		}
        	
        	
        	++row_;
        }
        
		return lNomencls;
	}

}
