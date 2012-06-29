package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XML {
	
	/** 
	 * Salva uma String num arquivo.
	 * É usado nas chamadas de carregar ou salvar arquivos.
	 * @param filename String com o caminho do arquivo para salvar
	 * @param string String a ser salva no arquivo
	 * @return true se salvou com sucesso e false caso não tenha sido salvo.
	 */
	public static boolean saveStringToFile(String filename, String string) throws IOException{
		boolean saved = false;
		
		BufferedWriter bw = null;		
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
		bw.write(string);
		saved = true;
		bw.close();
		return saved;
	}
	
	
	/**
	 * Lê o conteúdo de um arquivo.
	 * É usado nas chamadas de carregar ou salvar arquivos.
	 * @param filename Arquivo a ser lido
	 * @return A string do conteúdo do arquivo.
	 */
	public static String getStringFromFile(String filename) throws IOException{
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
		String s;
		while((s = br.readLine()) != null)
		{
			sb.append(s);
			sb.append("\n");
		}
		br.close();
		return sb.toString();
	}
	
	public static void toXML(Object o, String filename) throws IOException,XStreamException{
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(o);
		saveStringToFile(filename, xml);
	}
	
	public static Object fromXML(String filename) throws IOException,XStreamException{
		XStream xstream = new XStream(new DomDriver());
		String xml = getStringFromFile(filename);
		Object obj = xstream.fromXML(xml);
		return obj;
	}
}
