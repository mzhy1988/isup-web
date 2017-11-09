package com.hns.iusp.ws.xml;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 标准对象XML格式解析器
 * @author James Zhao
 *
 */

public class ObjectXmlMarshaller implements IXmLMarshaller {
	public final static String FORMAT="object";
	private boolean m_keepEmtpyTag = false;
	private boolean m_useHeadElement = false;
	public ObjectXmlMarshaller() {
		this(true,false);
	}
	
	public ObjectXmlMarshaller(boolean keepEmtpyTag){
		this(keepEmtpyTag,false);
	}
	
	public ObjectXmlMarshaller(boolean keepEmtpyTag,boolean useHeadElement){
		m_keepEmtpyTag = keepEmtpyTag;
		m_useHeadElement = useHeadElement;
	}
//	private static String replaceReservedChar(String text) {
//		if (text==null||text.length()==0)
//			return text;
//		String s = StringUtil.replace(text, "<", "&lt;");
//		s = StringUtil.replace(s, ">", "&gt;");
//		s = StringUtil.replace(s, "&", "&amp;");
//		s = StringUtil.replace(s, "'", "&apos;");
//		s = StringUtil.replace(s, "\"", "&quot;");
//		return s;
//	}
	
	/**  
     * 使用d替代非法字符.  
     * @param text 
     * @param d  
     * @return  
     */   
	private static String replaceInvaldateCharacter(String text, char d) {   
        if (text != null) {   
            char[] data = text.toCharArray();   
            for (int i = 0; i < data.length; i++) {   
                if(!isXMLCharacter(data[i]))  
                    data[i] = d;   
            }   
            return new String(data);   
        }   
        return "";   
    }   
      
    /**  
     * 使用空格替代非法字符.  
     * @param text 
     * @return  
     */   
	private static String replaceInvaldateCharacter(String text) {   
        return replaceInvaldateCharacter(text, (char)0x20);  
    }   
      
    /**  
     * 检查字符是否为合法的xml字符 
     * XML规范中规定了允许的字符范围(http://www.w3.org/TR/REC-xml#dt-character):  
     * Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]  
     * @param c  
     * @return  
     */   
    private static boolean isXMLCharacter(int c) {  
        if (c <= 0xD7FF) {   
            if (c >= 0x20)   
                return true;   
            else    
                return c == '\n' ||  c == '\r' || c == '\t';   
        }   
        return (c>=0xE000 && c<= 0xFFFD) || (c>=0x10000 && c<= 0x10FFFF);   
    }   	

    private static String toBase64( byte [] raw ) {
        if ( raw == null ||raw.length==0) {
          return "";
        }
        try {
    		return new String(Base64Codec.encode(raw),"UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		throw new RuntimeException(e);
    	}
    }	
    
	/**
	 * 16进制的字符串表示转成字节数组
	 * 
	 * @param base64String
	 *            16进制格式的字符串
	 * @return 转换后的字节数组
	 **/
    private static byte[] base64ToByteArray(String base64String) {
		if (base64String==null||base64String.length()==0)
			return new byte[0];
		try {
			return Base64Codec.decode(base64String.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}  
	
    
    private static String objectToString(Object obj){
    	if (obj==null)
    		return null;
    	else if (obj instanceof java.sql.Timestamp)
    		return DateUtil.toString((java.sql.Timestamp)obj,"yyyy-MM-dd HH:mm:ss");
    	else if (obj instanceof java.util.Date)
    		return DateUtil.toString((java.util.Date)obj,"yyyy-MM-dd");
    	else if (obj instanceof java.sql.Date)
    		return DateUtil.toString((java.sql.Date)obj,"yyyy-MM-dd");
    	else if (obj instanceof Boolean)
    		return ((Boolean)obj).booleanValue()?"Y":"N";
    	else if (
    			obj instanceof String
    			||obj instanceof Integer
    			||obj instanceof Long
    			||obj instanceof Short
    			||obj instanceof Float
    			||obj instanceof Double
    			||obj instanceof BigDecimal
    			){
    		return obj.toString();
    	}
    	else if (obj instanceof byte[]){
    		return toBase64((byte[])obj);
    	}
    	else
    		throw new IllegalArgumentException("Cann't convert class " + obj.getClass().getName() + " to string");
    }
    
    private static Object stringToObject(String s, Class c){
    	if (String.class==c){
    		return s;
    	}
    	else if (java.sql.Timestamp.class==c){
        	if (s==null||s.length()==0)
        		return null;
    		return DateUtil.toTimestamp(DateUtil.toDate(s,"yyyy-MM-dd HH:mm:ss"));
    	}
    	else if (java.util.Date.class == c){
        	if (s==null||s.length()==0)
        		return null;
    		return DateUtil.toDate(s,"yyyy-MM-dd");
    	}
    	else if (java.sql.Date.class == c){
        	if (s==null||s.length()==0)
        		return null;
    		return DateUtil.toSqlDate(DateUtil.toDate(s,"yyyy-MM-dd"));
    	}
    	else if (short.class==c){
        	if (s==null||s.length()==0)
        		return 0;
    		return Short.parseShort(s);
    	}
    	else if (Short.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new Short(s);
    	}
    	else if (int.class==c){
        	if (s==null||s.length()==0)
        		return 0;
    		return Integer.parseInt(s);
    	}
    	else if (Integer.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new Integer(s);
    	}
    	else if (long.class==c){
        	if (s==null||s.length()==0)
        		return 0L;
    		return Long.parseLong(s);
    	}
    	else if (Long.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new Long(s);
    	}
    	else if (float.class==c){
        	if (s==null||s.length()==0)
        		return 0;
    		return Float.parseFloat(s);
    	}
    	else if (Float.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new Float(s);
    	}
    	else if (double.class==c){
        	if (s==null||s.length()==0)
        		return 0;
    		return Double.parseDouble(s);
    	}
    	else if (Double.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
        		return new Double(s);
    	}
    	else if (BigDecimal.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new BigDecimal(s);
    	}
    	else if (boolean.class==c){
    		return "Y".equalsIgnoreCase(s);
    	}
    	else if (Boolean.class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return new Boolean("Y".equalsIgnoreCase(s));
    	}
    	else if (byte[].class==c){
    		if (s==null||s.length()==0)
    			return null;
    		else
    			return base64ToByteArray(s);
    	}
    	else
    		throw new IllegalArgumentException("Cann't convert string to class " + c.getName());
    }
    
    private static boolean isBaseClass(Class c){
    	if (String.class==c||java.sql.Timestamp.class==c||java.sql.Date.class == c||java.util.Date.class == c
    			||short.class==c||Short.class==c||int.class==c||Integer.class==c||long.class==c||Long.class==c
    			||float.class==c||Float.class==c||double.class==c||Double.class==c
    			||BigDecimal.class==c||boolean.class==c||Boolean.class==c||byte[].class==c)
    		return true;
    	else
    		return false;
    }
	
    private void fillXMLElement(Object po,Document doc,Node node) throws Exception{
    	if (po==null)
    		throw new Exception("Null po");
		Field[] fields = po.getClass().getDeclaredFields();
		Method[] methods = po.getClass().getDeclaredMethods();
		Node headNode = node;
		if (m_useHeadElement ){
			if (po.getClass().isAnnotationPresent(XmlHeadElement.class)){
				XmlHeadElement r = po.getClass().getAnnotation(XmlHeadElement.class);
				String headTag = "head";
				if (r.name()!=null&&r.name().length()>0&&!r.name().startsWith("#"))
					headTag = r.name();
				//仅当对象同时存在数组类型字段和基本类型字段时，才使用Head节点
				headNode = doc.createElement(headTag);
				node.appendChild(headNode);
			}
		}
		//先处理基本类型
		for (int k=0;k<fields.length;k++){
			Field field = fields[k];
			int mf = field.getModifiers();
			if (field.isAnnotationPresent(XmlTransient.class)
				||Modifier.isStatic(mf)
				||Modifier.isTransient(mf)
				||Modifier.isVolatile(mf)
				||Modifier.isInterface(mf))
				continue;
			if (isBaseClass(field.getType())){ //先处理基本类型
				boolean findMethod = false;
				for(int i=0;i<methods.length;i++){
					Method method = methods[i];
					if ((method.getName().equalsIgnoreCase("get"+field.getName())
						||(method.getName().toLowerCase().startsWith("is") 
							&& (method.getName().equalsIgnoreCase(field.getName())||
								method.getName().equalsIgnoreCase("is"+field.getName())))
						) //boolean type
							&& field.getType()==method.getReturnType()){
							//field
							findMethod = true;
							String tag = field.getName();
							if (method.isAnnotationPresent(XmlElement.class)){
								XmlElement el = method.getAnnotation(XmlElement.class);
								if (el.name()!=null&&el.name().length()>0){
									tag = el.name();
								}
							}
							Object rst = method.invoke(po, (Object[])null);
							if (rst==null){
								if (m_keepEmtpyTag){ //空值时是否保留标签
									Element ele =doc.createElement(tag);
									headNode.appendChild(ele);
								}
							}
							else {
								String s= objectToString(rst);
								if (s==null||s.length()==0)
									continue;
								Element ele =doc.createElement(tag);
								ele.setTextContent(replaceInvaldateCharacter(s));
								headNode.appendChild(ele);
							}
					}
				}
				if (findMethod==false){
					throw new Exception("Get method not found for field: " + field.getName());
				}
			}
			
		}
		
		for (int k=0;k<fields.length;k++){
			Field field = fields[k];
			int mf = field.getModifiers();
			if (field.isAnnotationPresent(XmlTransient.class)
				||Modifier.isStatic(mf)
				||Modifier.isTransient(mf)
				||Modifier.isVolatile(mf)
				||Modifier.isInterface(mf))
				continue;
			if (!isBaseClass(field.getType())){ //处理非基本类型
				boolean findMethod = false;
				for(int i=0;i<methods.length;i++){
					Method method = methods[i];
					if ((method.getName().equalsIgnoreCase("get"+field.getName())
						||(method.getName().toLowerCase().startsWith("is") && method.getName().equalsIgnoreCase(field.getName()))) //boolean type
							&& field.getType()==method.getReturnType()){
						findMethod = true;
						String tag = field.getName();
						if (method.isAnnotationPresent(XmlElement.class)){
							XmlElement el = method.getAnnotation(XmlElement.class);
							if (el.name()!=null&&el.name().length()>0){
								tag = el.name();
							}
						}
						
						Object rst = method.invoke(po, (Object[])null);
						if (rst==null){
							if (m_keepEmtpyTag){ //空值时是否保留标签
								Element ele =doc.createElement(tag);
								node.appendChild(ele);
							}
						}
						else if (field.getType()!=byte[].class && field.getType().isArray()||List.class.isAssignableFrom(field.getType())){
							//数组对象
							String subtag = "items";
							if (method.isAnnotationPresent(XmlElementWrapper.class)){
								XmlElementWrapper ew = method.getAnnotation(XmlElementWrapper.class);
								if (ew.name()!=null&&ew.name().length()>0){
									subtag = ew.name();
								}
							}
							Element subelement =doc.createElement(subtag);
							node.appendChild(subelement);
							if (rst instanceof Iterable){
								for (Object subpo: (Iterable)rst){
									Element ele =doc.createElement(tag);
									subelement.appendChild(ele);
									if (isBaseClass(field.getType().getComponentType())){
										String s= objectToString(subpo);
										if (s!=null&&s.length()>0)
											ele.setTextContent(replaceInvaldateCharacter(s));
									}
									else {
										fillXMLElement(subpo, doc, ele);
									}
								}
							}
							else if (rst.getClass().isArray()){
								int length = Array.getLength(rst);
								for (int l=0;l<length;l++){
									Element ele =doc.createElement(tag);
									subelement.appendChild(ele);
									if (isBaseClass(field.getType().getComponentType())){
										String s= objectToString(Array.get(rst, l));
										if (s!=null&&s.length()>0)
											ele.setTextContent(replaceInvaldateCharacter(s));
									}
									else {
										fillXMLElement(Array.get(rst, l), doc, ele);
									}
								}
							}
							
						}
						else {
							Element subelement =doc.createElement(tag);
							node.appendChild(subelement);
							fillXMLElement(rst, doc, subelement);
						}
						
					}
				}
				if (findMethod==false){
					throw new Exception("Get method not found for field: " + field.getName());
				}
			}
		}		
	}
	
//	public static String toXMLString(Document document) throws Exception{
//		if (document==null)
//			return "";
//		TransformerFactory   tf=TransformerFactory.newInstance();     
//		Transformer   transformer=tf.newTransformer();     
//		DOMSource   source=new  DOMSource(document);  
//		String encoding = ConfigManager.getConfiguration().getParameter("elink.ws.encoding", "UTF-8");
//		transformer.setOutputProperty(OutputKeys.ENCODING,encoding);     
//		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
//		transformer.setParameter("format-pretty-print",
//                                   new Boolean("true"));
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		OutputStreamWriter   pw=new   OutputStreamWriter(out,encoding);
//		try {
//			StreamResult   result=new   StreamResult(pw);     
//			transformer.transform(source,result);
//			pw.flush();
//			out.flush();
//		}finally {
//			pw.close();
//			out.close();
//		}
//		return new String(out.toByteArray(),encoding);
//	}
//	
//	public static Document toXML(String xmlString) throws Exception{
//		String encoding = ConfigManager.getConfiguration().getParameter("elink.ws.encoding", "UTF-8");
//		return toXML(xmlString, encoding);
//	}
//	public static Document toXML(String xmlString,String encoding) throws Exception{
//		if (xmlString==null||xmlString.length()==0)
//			return null;
//		DocumentBuilderFactory factory = null; 
//		DocumentBuilder builder = null; 
//		InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes(encoding));
//		
//		Document doc = null;
//		
//		try {
//			 factory=DocumentBuilderFactory.newInstance(); 
//			 builder=factory.newDocumentBuilder(); 
//			 doc = builder.parse(inputStream);
//			 return doc;
//		} finally{
//			inputStream.close();
//		}		
//	}
//	
	
	public <T>T fromXmlElement(Node element,Class<T> poClass,Map<String,Class> fieldMaps) throws Exception{
		NodeList nodes = element.getChildNodes();
		if (nodes==null||nodes.getLength()==0)
			return null;
		T po = poClass.newInstance();
		Field[] fields = po.getClass().getDeclaredFields();
		Method[] methods = po.getClass().getDeclaredMethods();
		//字段标签映射
		HashMap<String,Field> fieldTags = new HashMap<String,Field>();
		//子列表标签
		HashMap<String,Field> subListTags = new HashMap<String,Field>();
		//先建立标签对应关系
		for (int k=0;k<fields.length;k++){
			Field field = fields[k];
			int mf = field.getModifiers();
			if (field.isAnnotationPresent(XmlTransient.class)
				||Modifier.isStatic(mf)
				||Modifier.isTransient(mf)
				||Modifier.isVolatile(mf)
				||Modifier.isInterface(mf))
				continue;
			for(int i=0;i<methods.length;i++){
				Method method = methods[i];
				if ((method.getName().equalsIgnoreCase("get"+field.getName())
						||(method.getName().toLowerCase().startsWith("is") && 
						  (method.getName().equalsIgnoreCase(field.getName()) ||
						   method.getName().equalsIgnoreCase("is"+field.getName()) ))) //boolean type
							&& field.getType()==method.getReturnType()){
					if ((field.getType()!=byte[].class &&field.getType().isArray())||List.class.isAssignableFrom(field.getType())){
						//数组对象
						String tag = "items";
						if (method.isAnnotationPresent(XmlElementWrapper.class)){
							XmlElementWrapper ew = method.getAnnotation(XmlElementWrapper.class);
							if (ew.name()!=null&&ew.name().length()>0){
								tag = ew.name();
							}
						}
						subListTags.put(tag.toLowerCase(), field);

					}
					else {
						String tag = field.getName();
						if (method.isAnnotationPresent(XmlElement.class)){
							XmlElement el = method.getAnnotation(XmlElement.class);
							if (el.name()!=null&&el.name().length()>0){
								tag = el.name();
							}
						}
						fieldTags.put(tag.toLowerCase(), field);
					}
				}
			}
		}		
		
		for (int i=0;i<nodes.getLength();i++){
			String attrName = nodes.item(i).getNodeName();
			//#text为换行符
			if (attrName==null||attrName.length()==0||attrName.equalsIgnoreCase("#text"))
				continue;
			if (subListTags.containsKey(attrName.toLowerCase())){
				//数组
				Field field = subListTags.get(attrName.toLowerCase());
				NodeList childNodes = nodes.item(i).getChildNodes();
				if (field.getType()!=byte[].class && field.getType().isArray()){
					Class t ;
					if (fieldMaps!=null&&fieldMaps.containsKey(field.getName())){
						t = fieldMaps.get(field.getName());
					}
					else
						t = field.getType().getComponentType();
					int nodecount = 0;
					for (int l=0;l<childNodes.getLength();l++){
						if (childNodes.item(l).getNodeType()==Node.ELEMENT_NODE)
							nodecount++;
					}
					Object subpolist = Array.newInstance(t, nodecount);
					int nodeindex = 0;
					for (int l=0;l<childNodes.getLength();l++){
						Node node = childNodes.item(l);
						if (childNodes.item(l).getNodeType()!=Node.ELEMENT_NODE)
							continue;
						Object subpo ;
						if (isBaseClass(t)){
							subpo = stringToObject(node.getTextContent(), t); 
						}
						else {
							subpo = fromXmlElement(node, t,null);
						}
						Array.set(subpolist, nodeindex, subpo);
						nodeindex++;
					}
					if (!field.isAccessible()){
						field.setAccessible(true);
						field.set(po, subpolist);
						field.setAccessible(false);
					}
					else
						field.set(po, subpolist);
				}
				else if (List.class.isAssignableFrom(field.getType())){
					Class t ;
					if (fieldMaps!=null&&fieldMaps.containsKey(field.getName())){
						t = fieldMaps.get(field.getName());
					}
					else {
						Type pt = field.getGenericType();
						if (pt==null||!(pt instanceof ParameterizedType)){
							throw new RuntimeException("field " + field.getName()+" not GenericType");
						}
						t =(Class) ((ParameterizedType)pt).getActualTypeArguments()[0];
					}
					List subpolist = (List)field.getType().newInstance();
					for (int l=0;l<childNodes.getLength();l++){
						Node node = childNodes.item(l);
						if (childNodes.item(l).getNodeType()!=Node.ELEMENT_NODE)
							continue;
						Object subpo ;
						if (isBaseClass(t)){
							subpo = stringToObject(node.getTextContent(), t); 
						}
						else {
							subpo = fromXmlElement(node, t,null);
						}
						subpolist.add(subpo);
					}
					if (!field.isAccessible()){
						field.setAccessible(true);
						field.set(po, subpolist);
						field.setAccessible(false);
					}
					else
						field.set(po, subpolist);
				}
			}
			else if (fieldTags.containsKey(attrName.toLowerCase())){
				Field field = fieldTags.get(attrName.toLowerCase());
				if (isBaseClass(field.getType())){
					String value = nodes.item(i).getTextContent();
					if (!field.isAccessible()){
						field.setAccessible(true);
						field.set(po, stringToObject(value, field.getType()));
						field.setAccessible(false);
					}
					else
						field.set(po, stringToObject(value, field.getType()));
				}
				else {
					if (field.getType().isArray())
						throw new Exception("字段" + field.getName()+"是数组类型，不能实例化！");
					//字段不是基本类型
					Object subpo = fromXmlElement(nodes.item(i), field.getType(),null);
					if (!field.isAccessible()){
						field.setAccessible(true);
						field.set(po, subpo);
						field.setAccessible(false);
					}
					else
						field.set(po, subpo);
					
				}
			}
			else if (m_useHeadElement && "head".equalsIgnoreCase(attrName)){
				//head node
				NodeList childNodes = nodes.item(i).getChildNodes();
				if (childNodes==null)
					continue;
				for (int j=0;j<childNodes.getLength();j++){
					String childAttrName = childNodes.item(j).getNodeName();
					//#text为换行符
					if (childAttrName==null||childAttrName.length()==0||childAttrName.equalsIgnoreCase("#text"))
						continue;
					if (fieldTags.containsKey(childAttrName.toLowerCase())){
						Field field = fieldTags.get(childAttrName.toLowerCase());
						if (isBaseClass(field.getType())){
							String value = childNodes.item(j).getTextContent();
							if (!field.isAccessible()){
								field.setAccessible(true);
								field.set(po, stringToObject(value, field.getType()));
								field.setAccessible(false);
							}
							else
								field.set(po, stringToObject(value, field.getType()));
						}
						else {
							//字段不是基本类型
							Object subpo = fromXmlElement(childNodes.item(j), field.getType(),null);
							if (!field.isAccessible()){
								field.setAccessible(true);
								field.set(po, subpo);
								field.setAccessible(false);
							}
							else
								field.set(po, subpo);
							
						}
						
//						Field field = fieldTags.get(childAttrName.toLowerCase());
//						String value = childNodes.item(j).getTextContent();
//						if (!field.isAccessible()){
//							field.setAccessible(true);
//							field.set(po, stringToObject(value, field.getType()));
//							field.setAccessible(false);
//						}
//						else
//							field.set(po, stringToObject(value, field.getType()));
					}
				}
			}
			else {
				//未知标签
				continue;
			}

		}
		return po;
	}	

	public Document marshal(Object po) throws Exception {
		DocumentBuilderFactory   factory=DocumentBuilderFactory.newInstance();     
		DocumentBuilder builder=factory.newDocumentBuilder();   
		Document doc=builder.newDocument();
		String rootTag=po.getClass().getSimpleName();
		if (po.getClass().isAnnotationPresent(XmlRootElement.class)){
			XmlRootElement r = po.getClass().getAnnotation(XmlRootElement.class);
			if (r.name()!=null&&r.name().length()>0)
				rootTag = r.name();
		}
		Element root = doc.createElement(rootTag);
		doc.appendChild(root);
		fillXMLElement(po, doc, root);
		return doc;
	}
	
	
	public <T>T unmarshal(Document doc, Class<T> poClass,Map<String,Class> fieldMaps) throws Exception {
		Node root = doc.getDocumentElement();
		if (root==null)
			throw new Exception("Invalid document");
		return fromXmlElement(root, poClass,fieldMaps);

	}
}
