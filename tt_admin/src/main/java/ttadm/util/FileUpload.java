package ttadm.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ttadm.bean.AdminSessionBean;
import ttadm.model.*;
import ttadm.modelattribute.IMAmodel;
import ttadm.modelattribute.MA_loadNomencl;
import ttadm.modelattribute.MA_loadNomenclGroup;
import ttadm.modelattribute.MA_loadNomenclGroupRoot;
import ttadm.modelattribute.MA_loadProvider;
import ttadm.modelattribute.MA_loadTail;
import ttadm.service.TT_AdminServiceImpl;


@Service
public class FileUpload {
	
	
	
	@Autowired
	private TT_AdminServiceImpl ttadmService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	public ReadExcelFile ReadExcelFile;

	private static final String[][] ALLOWED_FILE_TYPES_PICS = {{"image/jpeg","jpeg"}, {"image/jpg","jpg"}, {"image/gif","gif"}};
	private static final String[][] ALLOWED_FILE_TYPES_XLS = {{"application/vnd.ms-excel","xls"},{"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xlsx"}};
    //private static final Long MAX_FILE_SIZE = 1048576L; //1MB
    //private static final String UPLOAD_FILE_PATH = "D:/GIT_/wood/src/main/webapp/resources/pics/";
    //private static final String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";
    private static File TEMP_FILE_PATH = null;


	private HashMap<Long,List<String>> hmPollPaths = new HashMap<Long,List<String>>(); //Список файлов на загрузку

    
    @Resource
    private Environment env;
    
    
    
    @PostConstruct
	void init(){
    	TEMP_FILE_PATH = Constants.tempDirectory;
		System.out.println("TEMP_FILE_PATH - " +TEMP_FILE_PATH);
	}
	
	@PreDestroy
	void destr() {
		System.out.println("FileUpload @PreDestroy ");
	}    

	
	
	public Collection<?>  process(IModel model , File file, IMAmodel IMAmodel) throws Exception {
            	
			Collection collection = null;
			try {

					if(model instanceof DirProvider)
						collection = ReadExcelFile.processFile(file,(DirProvider) model, (MA_loadProvider) IMAmodel) ;

					
					else if(model instanceof DirNomenclature)
					{
						List<DirNomenclGroup> lNG = ttadmService.getNomenclGroupList();
						HashMap<Long,DirNomenclGroup> hmNomenclGroup = new HashMap<Long,DirNomenclGroup>();
						for(DirNomenclGroup dng: lNG) 
							hmNomenclGroup.put(dng.getCode(), dng);
						
						List<DirGender> lDGen = ttadmService.getGenderList();
						HashMap<String,DirGender> hmDGen = new HashMap<String,DirGender>();
						for(DirGender dG: lDGen) 
							hmDGen.put(dG.getName(), dG);

						List<DirProvider> lDProv = ttadmService.getProviderList();
						HashMap<Long,DirProvider> hmDProv = new HashMap<Long,DirProvider>();
						for(DirProvider dP: lDProv)
							hmDProv.put(dP.getCode(),dP);
						
						collection = ReadExcelFile.processFile(file,(DirNomenclature) model, (MA_loadNomencl) IMAmodel, hmNomenclGroup, hmDGen, hmDProv) ;
						hmPollPaths = ReadExcelFile.getHmPollPaths(); //Список файлов на загрузку

					}

					
					else if(model instanceof DirNomenclGroup)
					{
						List<DirNomenclGroupRoot> lNGR = ttadmService.getNomenclGroupRootList();
						HashMap<Long,DirNomenclGroupRoot> hmNomenclGroupRoot = new HashMap<Long,DirNomenclGroupRoot>();
						
						for(DirNomenclGroupRoot dngr: lNGR) 
							hmNomenclGroupRoot.put(dngr.getCode(), dngr);
					
						collection = ReadExcelFile.processFile(file,(DirNomenclGroup) model, (MA_loadNomenclGroup) IMAmodel, hmNomenclGroupRoot) ;
					}

					
					else if(model instanceof DirNomenclGroupRoot)
						collection = ReadExcelFile.processFile(file,(DirNomenclGroupRoot) model, (MA_loadNomenclGroupRoot) IMAmodel) ;

					
					else if(model instanceof Tail)
					{

						List<DirNomenclature> lN = ttadmService.getNomenclatureList();
						HashMap<Long,DirNomenclature> hmNomencl = new HashMap<Long,DirNomenclature>();
						for(DirNomenclature dn: lN) 
							hmNomencl.put(dn.getCode(), dn);

						
						collection = ReadExcelFile.processFile(file,(Tail) model, (MA_loadTail) IMAmodel, hmNomencl) ;
					}
					
					file.delete();
					return collection;
				
			} 
			catch(Exception exc)
			{
					file.delete();
					exc.printStackTrace();
					throw exc;
			}
		

	}

    
	public void downloadPhoto (long code, List<String> files) throws Exception
	{
		try {
			
				File rootFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code);
				if(!rootFolder.exists() && !rootFolder.mkdirs()) throw new Exception("Can not create rootFolder! ");
				
				//System.out.println("rootFolder - "+rootFolder);
				
				File largeFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"L");
				if(!largeFolder.exists() && !largeFolder.mkdirs()) throw new Exception("Can not create largeFolder! ");
				
				//System.out.println("largeFolder - "+largeFolder);
				
				File mediumFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"M");
				if(!mediumFolder.exists() && !mediumFolder.mkdirs()) throw new Exception("Can not create mediumFolder! ");
				
				//System.out.println("mediumFolder - "+mediumFolder);
				
				File smallFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"S");
				if(!smallFolder.exists() && !smallFolder.mkdirs()) throw new Exception("Can not create smallFolder! ");
				
				//System.out.println("smallFolder - "+smallFolder);
				
			
				Iterator<String> iter = files.iterator();
				int i=0;
				
				while(iter.hasNext() )
				{
						File file = new File(iter.next());
						long time = System.currentTimeMillis();
						
						Path path = Paths.get(file.toURI());
						
						try {

							byte[] data = Files.readAllBytes(path);
							
							File tempFile = new File(TEMP_FILE_PATH+File.separator+code+".tmp");
							
							path = Paths.get(tempFile.toURI());
							Files.write(path, data);
							
							BufferedImage img = null;
							
							try {
								img = ImageIO.read(path.toFile());
							}
							catch(javax.imageio.IIOException err) {
								throw new javax.imageio.IIOException("Неправильная кодировка файла jpg     " + file);
							}
							
							float img_width = img.getWidth();
							float img_height = img.getHeight();
							float img_ratio =  img_height / img_width;
							
							//System.out.println("img_width - "+ img_width+",   img_height - "+img_height +",    img_ratio - " +(float)img_ratio);
							
							
							ImageIO.write(img, "jpg", new File(largeFolder+File.separator+code+"_L_"+i+".jpg"));
							
							int M_width = 390;
							int S_width = 190;
							
							int M_height = 582;
							int S_height = 282;
							
							int rate = img.getWidth() / M_width;
							
							//img = scaleImage(img,M_width, (int)(M_width*(float)img_ratio));
							img = scaleImage(img,(int)(M_height/(float)img_ratio),M_height);
							ImageIO.write(img, "jpg", new File(mediumFolder+File.separator+code+"_M_"+i+".jpg"));

							rate = img.getWidth() / S_width;
							
							//img = scaleImage(img,S_width, (int)(S_width*(float)img_ratio));
							img = scaleImage(img,(int)(S_height/(float)img_ratio), S_height);
							ImageIO.write(img, "jpg", new File(smallFolder+File.separator+code+"_S_"+i+".jpg"));
							
							tempFile.delete();
							
							System.out.println("Code - "+code +" :  "+file+" time - " +(System.currentTimeMillis() - time)/1000+ " sec.");
							++i;
						}
						catch(java.nio.file.FileSystemException fse) {
							System.err.println("\n========= ERROR: FileUpload.downloadPhoto =======");
							fse.printStackTrace(System.err);
							System.err.println("\n========= ERROR: FileUpload.downloadPhoto =======");
							throw new Exception("ERROR: FileUpload.downloadPhoto  not found - "+file);
						}
				}

			}
			catch(java.io.FileNotFoundException fnf)
			{
				fnf.getMessage();
				throw new Exception("ERROR: FileUpload.downloadPhoto!");
			}
			catch(NullPointerException e) {
				System.err.println("\n========= ERROR: FileUpload.downloadPhoto =======");
				 //System.out.println("Catalog not found - "+files);
				 e.printStackTrace(System.err);
				System.err.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
				throw new Exception("ERROR: FileUpload.downloadPhoto!");
			}
			catch(Exception e) {
				System.err.println("\n========= ERROR: FileUpload.downloadPhoto =======");
				 //System.out.println("pathToShare - "+files);
				 e.printStackTrace(System.err);
				System.err.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
				throw new Exception(e.getMessage());
			}

	}
	
	
	
	public void downloadPhoto (long code, String pathToShare) 
	{
		
		final ExtensionsFilter IMAGE_FILTER =  new FileUpload.ExtensionsFilter(new String[] {".jpg"});
		
		try {
			
			
			File[] files = new File(pathToShare).listFiles(IMAGE_FILTER); 
			
			File rootFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code);
			if(!rootFolder.exists() && !rootFolder.mkdirs()) throw new Exception("Can not create rootFolder! ");
			
			File largeFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"L");
			if(!largeFolder.exists() && !largeFolder.mkdirs()) throw new Exception("Can not create largeFolder! ");

			//System.out.println("largeFolder - "+largeFolder);

			
			File mediumFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"M");
			if(!mediumFolder.exists() && !mediumFolder.mkdirs()) throw new Exception("Can not create mediumFolder! ");

			//System.out.println("mediumFolder - "+mediumFolder);

			
			File smallFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"S");
			if(!smallFolder.exists() && !smallFolder.mkdirs()) throw new Exception("Can not create smallFolder! ");
			
			//System.out.println("smallFolder - "+smallFolder);


			for(int i=0; i < files.length; ++i)
			{
					long time = System.currentTimeMillis();
					
					Path path = Paths.get(files[i].toURI());
					byte[] data = Files.readAllBytes(path);
					
					File tempFile = new File(TEMP_FILE_PATH+File.separator+code+".tmp");
					
					path = Paths.get(tempFile.toURI());
					Files.write(path, data);
					
					BufferedImage img = ImageIO.read(path.toFile());
					ImageIO.write(img, "jpg", new File(largeFolder+File.separator+code+"_L_"+i+".jpg"));
					ImageIO.write(scaleImage(img,389,582), "jpg", new File(mediumFolder+File.separator+code+"_M_"+i+".jpg"));
					ImageIO.write(scaleImage(img,189,282), "jpg", new File(smallFolder+File.separator+code+"_S_"+i+".jpg"));
					
					tempFile.delete();
					
					System.out.println("Code - "+code +" :  "+files[i]+" time - " +(System.currentTimeMillis() - time)/1000+ " sec.");
			}
		}
		catch(java.io.FileNotFoundException fnf)
		{
			fnf.getMessage();
		}
		catch(NullPointerException e) {
			System.out.println("\n========= ERROR: FileUpload.downloadPhoto =======");
			 System.out.println("Catalog not found - "+pathToShare);
			 e.printStackTrace(System.out);
			System.out.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
		}
		catch(Exception e) {
			System.out.println("\n========= ERROR: FileUpload.downloadPhoto =======");
			 System.out.println("pathToShare - "+pathToShare);
			 e.printStackTrace(System.out);
			System.out.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
		}
		
	}

	
	public void downloadPhoto (long code, MultipartFile file) 
	{
		
		File tempFile = null;
		
		try {
			
			
			File rootFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code);
			if(!rootFolder.exists() && !rootFolder.mkdirs()) throw new Exception("Can not create rootFolder! ");
			
			//System.out.println("rootFolder - "+rootFolder);
			
			File largeFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"L");
			if(!largeFolder.exists() && !largeFolder.mkdirs()) throw new Exception("Can not create largeFolder! ");

			//System.out.println("largeFolder - "+largeFolder);

			
			File mediumFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"M");
			if(!mediumFolder.exists() && !mediumFolder.mkdirs()) throw new Exception("Can not create mediumFolder! ");

			//System.out.println("mediumFolder - "+mediumFolder);

			
			File smallFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"S");
			if(!smallFolder.exists() && !smallFolder.mkdirs()) throw new Exception("Can not create smallFolder! ");
			
			//System.out.println("smallFolder - "+smallFolder);

			int countFiles = largeFolder.listFiles().length;
			
			tempFile = new File(TEMP_FILE_PATH+File.separator+code+".tmp");
			
			file.transferTo(tempFile);

			long time = System.currentTimeMillis();
					
			Path path = Paths.get(tempFile.toURI());
			byte[] data = Files.readAllBytes(path);
					
			
					
			path = Paths.get(tempFile.toURI());
			Files.write(path, data);
					
			BufferedImage img = ImageIO.read(path.toFile());
			ImageIO.write(img, "jpg", new File(largeFolder+File.separator+code+"_L_"+ countFiles +".jpg"));
			ImageIO.write(scaleImage(img,389,582), "jpg", new File(mediumFolder+File.separator+code+"_M_"+countFiles+".jpg"));
			ImageIO.write(scaleImage(img,189,282), "jpg", new File(smallFolder+File.separator+code+"_S_"+countFiles+".jpg"));
					
			tempFile.delete();
					
			System.out.println("Code - "+code +" :  "+tempFile+" time - " +(System.currentTimeMillis() - time)/1000+ " sec.");
		}
		catch(java.io.FileNotFoundException fnf)
		{
			System.out.println("\n========= ERROR: FileUpload.downloadPhoto =======");
			fnf.printStackTrace(System.out);
			fnf.getMessage();
		}
		catch(NullPointerException e) {
			System.out.println("\n========= ERROR: FileUpload.downloadPhoto =======");
			 System.out.println("Catalog not found - "+tempFile);
			 e.printStackTrace(System.out);
			System.out.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
		}
		catch(Exception e) {
			System.out.println("\n========= ERROR: FileUpload.downloadPhoto =======");
			 System.out.println("pathToShare - "+tempFile);
			 e.printStackTrace(System.out);
			System.out.println("========= ERROR: FileUpload.downloadPhoto ======= \n\n");
		}
		
	}
	
	public boolean deletePhoto(Long code, int file_number) {
		
		File largeFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"L");

		File mediumFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"M");
		
		File smallFolder = new File(Constants.UPLOAD_FILE_PATH+File.separator+code+File.separator+"S");
		
		return new File(largeFolder+File.separator+code+"_L_"+ file_number +".jpg").delete() && 
					new File(mediumFolder+File.separator+code+"_M_"+file_number+".jpg").delete() && 
							new File(smallFolder+File.separator+code+"_S_"+file_number+".jpg").delete();
	}
	
	public BufferedImage scaleImage(BufferedImage img, int targetWidth, int targetHeight) {

	    int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	    BufferedImage ret = img;
	    BufferedImage scratchImage = null;
	    Graphics2D g2 = null;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    int prevW = w;
	    int prevH = h;

	    do {
	        if (w > targetWidth) {
	            w /= 2;
	            w = (w < targetWidth) ? targetWidth : w;
	        }
	        else if(w < targetWidth)
	        	targetWidth = w;
	        
	        if (h > targetHeight) {
	            h /= 2;
	            h = (h < targetHeight) ? targetHeight : h;
	        }
	        else if(h < targetHeight)
	        	targetHeight = h;
	        
	        if (scratchImage == null) {
	            scratchImage = new BufferedImage(w, h, type);
	            g2 = scratchImage.createGraphics();
	        }

	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

	        prevW = w;
	        prevH = h;
	        ret = scratchImage;
	    } while (w != targetWidth || h != targetHeight);

	    if (g2 != null) {
	        g2.dispose();
	    }

	    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	        g2 = scratchImage.createGraphics();
	        g2.drawImage(ret, 0, 0, null);
	        g2.dispose();
	        ret = scratchImage;
	    }

	    return ret;

	}
	
	
	
	private String isValidContentType(String[][] ALLOWED_FILE_TYPES,String contentType) {
    	//System.out.println("contentType - "+contentType);
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return ext[1]; 
        
        return null;
    }

	
	private class ExtensionsFilter implements FileFilter 
	{
	    private char[][] extensions;

	    private ExtensionsFilter(String[] extensions)
	    {
	        int length = extensions.length;
	        this.extensions = new char[length][];
	        for (String s : extensions)
	        {
	            this.extensions[--length] = s.toCharArray();
	        }
	    }

	    @Override
	    public boolean accept(File file)
	    {
	        char[] path = file.getPath().toCharArray();
	        for (char[] extension : extensions)
	        {
	            if (extension.length > path.length)
	            {
	                continue;
	            }
	            int pStart = path.length - 1;
	            int eStart = extension.length - 1;
	            boolean success = true;
	            for (int i = 0; i <= eStart; i++)
	            {
	                if ((path[pStart - i] | 0x20) != (extension[eStart - i] | 0x20))
	                {
	                    success = false;
	                    break;
	                }
	            }
	            if (success)
	                return true;
	        }
	        return false;
	    }
	}


	public HashMap<Long, List<String>> getHmPollPaths() {
		return hmPollPaths;
	}
	
	
	
	
}
