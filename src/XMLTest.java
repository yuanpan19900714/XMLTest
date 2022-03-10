import java.io.*;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;

public class XMLTest {
	public static void fileRead(String filePath) {
		File file = new File(filePath);
		BufferedReader bReader = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			bReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s = bReader.readLine()) != null) {
				sb.append(s + "\n");
			}
			String str = sb.toString();
			String str1 = str.substring(0, 7);
			String str2 = str.substring(7);
			Document doc = DocumentHelper.parseText(str2);
			Element root = doc.getRootElement(); // 获取根元素

			Element bj = DocumentHelper.createElement("班级");
			bj.addAttribute("id", "3-5");
			bj.addAttribute("name", "三年五班");
			@SuppressWarnings("unchecked")
			List<Element> students = root.elements("学生");
			root.clearContent();
			root.add(bj);
			for (Element student : students) {
				bj.add(student);
			}
			//Node node = doc.selectSingleNode("//学生[@性别 = '男']");
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GB2312");
			format.setSuppressDeclaration(true);
			format.setIndent(true);
			format.setIndent("   ");
			format.setNewlines(true);
			String newStr = doc.asXML();
			System.out.println("str1:\n" + str1);
			System.out.println("str2:\n" + str2);
			System.out.println("newStr:\n" + newStr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String filePath = "student.xml";
		try {
			fileRead(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
