package custom.acmer.infile;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.StringReader;
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class PageAnalyser {
	final static String templateUrl = "https://cn.vjudge.net/problem/description/";
	
	public String getHtmlContent(String htmlurl) {   //获取原网页内容
		URL url;
		URLConnection urlCon;
		String temp;
		StringBuffer sb = new StringBuffer();
		try {
				System.setProperty("http.agent", "Chrome");
				url = new URL(htmlurl);
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));// 读取网页全部内容  
	            while ((temp = in.readLine()) != null) {  
	                sb.append(temp+'\n');  
	            }  
	            in.close();  
	        } catch (final MalformedURLException me) {  
	            System.out.println("你输入的URL格式有问题!");  
	            me.getMessage();  
	        } catch (final IOException e) {  
	            e.printStackTrace();  
	        }  
	        return sb.toString();  
	    }
	
	public String getSampleInput(String html) {					//从描述网页中获取输入
		String regex = "Sample.*pre(.*)/pre.*Sample";
		Pattern pa = Pattern.compile(regex);
		BufferedReader br = new BufferedReader(new StringReader(html));
		String n,result=" ";
		try {
			while((n = br.readLine() )!= null) {
				Matcher ma = pa.matcher(n);
				while(ma.find()) {
					//System.out.println(ma.group(1));
					result = ma.group(1);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;    
		
	}
	public List<String> getPublicDescId(String html) {			//从原网页中获取DescID 用于获取描述页的url地址
		List<String> descId = new ArrayList<String>();
		String regex = "publicDescId\":(\\d*),";
		Pattern pa = Pattern.compile(regex);
		
		BufferedReader br = new BufferedReader(new StringReader(html));
		String n;
		try {
			while((n = br.readLine() )!= null) {
				Matcher ma = pa.matcher(n);
				while(ma.find()) {
					descId.add(ma.group(1));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return descId;
	}
	
	private String getIndexByUrl(char a,List<String> list) {
		return list.get(a-'A');
	}
	
	private char getCate(String s) {
		return s.charAt(s.length()-1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PageAnalyser pa = new PageAnalyser();
		String htmlUrl = "https://cn.vjudge.net/contest/154511#problem/D";
		char proCate = pa.getCate(htmlUrl);
		String pageContent = pa.getHtmlContent(htmlUrl);
		List<String> publicDescId = pa.getPublicDescId(pageContent);
		String decriUrl = PageAnalyser.templateUrl + pa.getIndexByUrl(proCate, publicDescId);
		String decriContent = pa.getHtmlContent(decriUrl);
		String inFileContent = pa.getSampleInput(decriContent);
		//System.out.println(pa.getSampleInput(content));
		//System.out.println(pa.getPublicDescId(pageContent));
		System.out.println(inFileContent);
	}

}
