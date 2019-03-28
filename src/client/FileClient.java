package client;

import corba.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FileClient {
    public static List<String> getClienAllFileNames(String dirPath) {
        List<String> fileNameList = new ArrayList<>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // ���ļ�������Ŀ¼�ļ���
                if (files[i].isFile()) {
                    fileNameList.add(files[i].getName());
                }
            }
        }
        return fileNameList;
    }

    public static void printMenu() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
        System.out.println("*\t\t\t\t\t\t\t\t�ֲ�ʽ�ļ�ϵͳ\t\t\t\t\t\t\t\t\t\t\t\t*");
        System.out.println("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t*");
        System.out.println("*\t\t1.�ͻ����ļ��б�\t\t2.�������ļ��б�\t\t3.�����ļ�\t\t4.�ϴ��ļ�\t\t5.�˳�*");
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //ָ��ORB��ip��ַ
            properties.put("org.omg.CORBA.ORBInitialPort", "1050");       //ָ��ORB�Ķ˿�
            // �½�ORB����
            ORB orb = ORB.init(args, properties);

            // Naming ������
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // ��Namingע������л�ȡ��Զ�̶���
            String name = "FileOps";
            TFile remoteObj = TFileHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtained a handle on server object");

            Scanner input = new Scanner(System.in);
            boolean m = true;   //����whileѭ��
            int n;              //switch�ж�

            while (m) {
                printMenu();
                System.out.println("ѡ��һ���:");
                n = input.nextInt();
                // ���˵�nextInt()����Ŀ��ַ� ref��https://blog.csdn.net/wjy1090233191/article/details/42080029
                input.nextLine();
                switch (n) {
                    case 1:
                        List<String> allFiles = getClienAllFileNames("clientFile/");
                        for (String str : allFiles) {
                            System.out.println(str);
                        }
                        break;
                    case 2:
                        // resultList��һ�����ز���
                        MyListHolder resultList = new MyListHolder();
                        remoteObj.listAllFile(resultList);
                        for (int i = 0; i < resultList.value.length; i++) {
                            System.out.println(resultList.value[i]);
                        }
                        break;
                    case 3:
                        System.out.println("������Ҫ���ص��ļ�����������׺����:");
                        String dFileName = input.nextLine();
                        // result��һ�����ز���
                        MyDataHolder result = new MyDataHolder();
                        boolean downloadFlag = remoteObj.download("serverFile/" +dFileName, result);
                        if (downloadFlag) {
                            // ���浽�ͻ����ļ�ϵͳ
                            FileOutputStream fos = new FileOutputStream("clientFile/" + dFileName);
                            fos.write(result.value);
                            fos.flush();
                            fos.close();
                            System.out.println("���سɹ�!");
                        } else {
                            System.out.println("����ʧ��!����������ļ��ڷ��������Ƿ����");
                        }
                        break;
                    case 4:
                        System.out.println("������Ҫ�ϴ����ļ�����������׺���������ϴ������ļ��У�������ѹ���ļ�:");
                        String uFileName = input.nextLine();

                        MyDataHolder in = new MyDataHolder();
                        try {
                            // �ӿͻ����ļ��õ��ֽ���
                            FileInputStream fis = new FileInputStream("clientFile/" + uFileName);
                            byte[] data = new byte[fis.available()];
                            fis.read(data);
                            fis.close();
                            in.value = data;
                            remoteObj.upload(uFileName, in.value);
                            System.out.println("�ϴ��ɹ�!");
                            break;
                        } catch (Exception e) {
                            System.out.println("�ϴ�ʧ��!����������ļ��ڷ��������Ƿ����");
                            break;
                        }

                    case 5:
                        m = false;
                        break;
                    default:
                        System.out.println("������������ԣ�");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
