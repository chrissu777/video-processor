package com.weaponwatch.vidprocessor;

import com.weaponwatch.vidprocessor.aws.S3Service;

public class UploadTest {
    public static void main(String[] args){
        S3Service s3Service = new S3Service();
        try{
            s3Service.uploadVideo("weaponwatch-demo", "test-object1", "test.txt");
        }catch(Exception e){
            System.out.println("Bruh");
        }
    }
}
