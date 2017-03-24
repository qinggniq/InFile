package custom.acmer.infile;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 

/*import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.StringReader;
*/
public class InFileGenerator {
	private PageAnalyser pageAnalyser;
	
	
	 InFileGenerator(String url,String path) {
		pageAnalyser = new PageAnalyser(url);
		PrintWriter pw;
		try {
			pw = new PrintWriter(path);
			pw.write(pageAnalyser.getInFileContent());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InFileGenerator ifG = new InFileGenerator("https://cn.vjudge.net/contest/155180#problem/A","./in.txt");
		// "Successed!\\n";
		
		System.out.println("adada");
	}

}
