package corba;


/**
* corba/TFileOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��TFile.idl
* 2019��3��27�� ������ ����09ʱ12��11�� CST
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
