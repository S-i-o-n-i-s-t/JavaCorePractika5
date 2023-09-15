package dz;

import java.io.*;

public class BackupСopy {
   /**
    * 1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
    * 2. Доработайте класс Tree и метод print который мы разработали на семинаре.
    *    Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
    */
   private static final String SOURCE_DIR = ".";
   private static final String BACHUP_DIR = "./out/copy";
   public static void main(String[] args){
      try {
         createBackup(SOURCE_DIR, BACHUP_DIR);
         System.out.println("Резервная копия создана успешно.");
      } catch (IOException e) {
         System.out.println("Ошибка при создании резервной копии: " + e.getMessage());
      }
      Tree.print(new File("."), "", true);
   }
   public static void createBackup(String sourceDir, String backupDir)throws IOException{
      File sourceDirectory = new File(sourceDir);
      File backupDirectory = new File(backupDir);
      //Проверяем, является ли файл или каталог, обозначаемый абстрактным именем файла, каталогом или нет
      if (!sourceDirectory.isDirectory()){
         throw new IllegalArgumentException("Указанный путь не указывает на каталог");
      }
       //Проверяем, существует ли файл или каталог, обозначаемый абстрактным именем файла, или нет.
      if (!backupDirectory.exists()){
         // Функция mkdirs() используется для создания нового каталога, обозначаемого абстрактным путем,
         // а также всех несуществующих родительских каталогов с абстрактным путем.
         // Если функции mkdirs() не удается создать какой-либо каталог, возможно, она создала какой-то из своих родительских каталогов.
         // Функция возвращает true, если каталог создан, иначе возвращает false.
         backupDirectory.mkdir();
      }
      // Функция listFiles() возвращает массив файлов, обозначающих файлы по заданному абстрактному пути,
      // если имя пути является каталогом, в противном случае возвращается значение null.
      // Функция является перегруженной функцией. Одна из функций не имеет никакого параметра,
      // вторая функция принимает объект FilenameFilter в качестве параметра, третья функция принимает объект FileFilter в качестве параметра
      File[] files = sourceDirectory.listFiles(new MyFileNameFilter(".txt"));
      if (files.length != 0){
         for (File file: files){
            File backupFile = new File(backupDir + "/" + file.getName());
            copyFile(file, backupFile);
         }
      }
   }
//System.out.println(Arrays.toString(directories));
   /**
    * Метод копирования файлов
    * @param sourceFile оригинал
    * @param destinationFile копия
    * @throws IOException
    */
   public static void copyFile(File sourceFile, File destinationFile) throws IOException {
      try (FileInputStream inputStream = new FileInputStream(sourceFile);
           FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
         byte[] buffer = new byte[1024];
         int length;
         while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
         }
      }
   }
}















