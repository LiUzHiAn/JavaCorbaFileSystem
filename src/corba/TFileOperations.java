package corba;


/**
* corba/TFileOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/


// all file names in server available to download
public interface TFileOperations 
{

  /* fileData:?????A */
  boolean download (String fileName, corba.MyDataHolder fileData);

  /* fileData:?7??e?A */
  boolean upload (String fileName, byte[] fileData);

  /* ?h?@	?}??? */
  void listAllFile (corba.MyListHolder allFileNames);
} // interface TFileOperations
