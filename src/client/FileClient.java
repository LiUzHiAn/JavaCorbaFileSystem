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
                // 是文件（跳过目录文件）
                if (files[i].isFile()) {
                    fileNameList.add(files[i].getName());
                }
            }
        }
        return fileNameList;
    }

    public static void printMenu() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
        System.out.println("*\t\t\t\t\t\t\t\t分布式文件系统\t\t\t\t\t\t\t\t\t\t\t\t*");
        System.out.println("*\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t*");
        System.out.println("*\t\t1.客户端文件列表\t\t2.服务器文件列表\t\t3.下载文件\t\t4.上传文件\t\t5.退出*");
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
            properties.put("org.omg.CORBA.ORBInitialPort", "1050");       //指定ORB的端口
            // 新建ORB对象
            ORB orb = ORB.init(args, properties);

            // Naming 上下文
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // 从Naming注册服务中获取到远程对象
            String name = "FileOps";
            TFile remoteObj = TFileHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtained a handle on server object");

            Scanner input = new Scanner(System.in);
            boolean m = true;   //用于while循环
            int n;              //switch判断

            while (m) {
                printMenu();
                System.out.println("选择一项功能:");
                n = input.nextInt();
                // 过滤掉nextInt()后面的空字符 ref：https://blog.csdn.net/wjy1090233191/article/details/42080029
                input.nextLine();
                switch (n) {
                    case 1:
                        List<String> allFiles = getClienAllFileNames("clientFile/");
                        for (String str : allFiles) {
                            System.out.println(str);
                        }
                        break;
                    case 2:
                        // resultList是一个返回参数
                        MyListHolder resultList = new MyListHolder();
                        remoteObj.listAllFile(resultList);
                        for (int i = 0; i < resultList.value.length; i++) {
                            System.out.println(resultList.value[i]);
                        }
                        break;
                    case 3:
                        System.out.println("输入想要下载的文件名（包括后缀名）:");
                        String dFileName = input.nextLine();
                        // result是一个返回参数
                        MyDataHolder result = new MyDataHolder();
                        boolean downloadFlag = remoteObj.download("serverFile/" +dFileName, result);
                        if (downloadFlag) {
                            // 保存到客户端文件系统
                            FileOutputStream fos = new FileOutputStream("clientFile/" + dFileName);
                            fos.write(result.value);
                            fos.flush();
                            fos.close();
                            System.out.println("下载成功!");
                        } else {
                            System.out.println("下载失败!请检查输入的文件在服务器上是否存在");
                        }
                        break;
                    case 4:
                        System.out.println("输入想要上传的文件名（包括后缀名），若上传的是文件夹，请打包成压缩文件:");
                        String uFileName = input.nextLine();

                        MyDataHolder in = new MyDataHolder();
                        try {
                            // 从客户端文件得到字节流
                            FileInputStream fis = new FileInputStream("clientFile/" + uFileName);
                            byte[] data = new byte[fis.available()];
                            fis.read(data);
                            fis.close();
                            in.value = data;
                            remoteObj.upload(uFileName, in.value);
                            System.out.println("上传成功!");
                            break;
                        } catch (Exception e) {
                            System.out.println("上传失败!请检查输入的文件在服务器上是否存在");
                            break;
                        }

                    case 5:
                        m = false;
                        break;
                    default:
                        System.out.println("输入错误，请重试！");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
