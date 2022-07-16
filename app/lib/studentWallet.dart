import 'package:app/variables.dart';
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;

class StudentWallet {
  static String privateKey;
  static String credentialAddress;

  StudentWallet(String key, String address) {
    privateKey = key;
    credentialAddress = address;
  }

  String get getPrivateKey => privateKey;
  String get getCredAddress => credentialAddress;

  static Future<StudentWallet> newStudent() async {

    try {
      var value = await Dio().get('http://www.google.com');
      print(value);
    } catch (e) {
      print(e);
    }
    
      http.Response value =
          await http.get(url + ':8080/newStudentWallet');
      print("Student Wallet: ${value.body}");
      privateKey = value.body.split(" ")[0];
      credentialAddress = value.body.split(" ")[1];
  
   
    return StudentWallet(privateKey, credentialAddress);
  }
}
