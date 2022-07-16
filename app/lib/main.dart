import 'package:app/homepage.dart';
import 'package:flutter/material.dart';
import 'package:flutter_downloader/flutter_downloader.dart';
import 'dart:convert';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart' as crypto;

///Generate MD5 hash
generateMd5(String data) {
  var content = new Utf8Encoder().convert(data);
  var md5 = crypto.md5;
  var digest = md5.convert(content);
  return hex.encode(digest.bytes);
}

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await FlutterDownloader.initialize(debug: true);

  runApp(MyApp());
}

// class MyApp extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       debugShowCheckedModeBanner: false,
//       title: "Exam App",
//       home: HomePage(),
//       theme: ThemeData.dark(),
//     );
//   }
// }

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Final Year Project",
      debugShowCheckedModeBanner: false,
      home: LoginPage(),
      theme: ThemeData.dark(),
    );
  }
}

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool _isHidden = true;
  bool checkBoxValue = false;
  bool loginBool = false;
  final _email = TextEditingController();
  final _passwordController = TextEditingController();

  void _toggleVisibility() {
    setState(() {
      _isHidden = !_isHidden;
    });
  }

  @override
  void initState() {
    super.initState();
  }

  void submit(TextEditingController _email, TextEditingController _password) {
    String email = _email.text;
    String password = _password.text;
    print(password);
    print(email);
    String hashedPassword = generateMd5(password);
    print(hashedPassword);
    if (hashedPassword == "0e385a520de8f64ddcec5ee4e7d4aa55") {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => HomePage()),
      );
    }
    return;
  }

  hexColor<Color>(String colorhexcode) {
    String colornew = '0xff' + colorhexcode;
    colornew = colornew.replaceAll('#', '');
    int colorint = int.parse(colornew);
    return colorint;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: new Scaffold(
        resizeToAvoidBottomPadding: true,
        body: Container(
          decoration: BoxDecoration(
              gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                Color(hexColor('#1f6ab4')),
                Color(hexColor('#7bc2e8'))
              ])),
          child: Card(
            child: Container(
              alignment: Alignment.center,
              decoration: BoxDecoration(
                  gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      colors: [
                    Color(hexColor('#1f6ab4')),
                    Color(hexColor('#7bc2e8'))
                  ])),
              padding: EdgeInsets.only(
                  top: 20.0, right: 20.0, left: 20.0, bottom: 20.0),
              child: ListView(
                // crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Container(
                        child: Image.asset(
                          'assets/logo.png',
                          width: 150,
                        ),
                        decoration: BoxDecoration(
                          borderRadius: new BorderRadius.circular(300.0),
                          border: new Border.all(
                            width: 5.0,
                            color: Color(hexColor('#D2E1F0')),
                          ),
                        ),
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 40.0,
                  ),
                  SizedBox(
                    height: 40.0,
                  ),
                  buildTextField("Email"),
                  SizedBox(
                    height: 20.0,
                  ),
                  buildTextField("Password"),
                  SizedBox(
                    height: 20.0,
                  ),
                  SizedBox(height: 50.0),
                  buildButtonContainer(),
                  SizedBox(
                    height: 10.0,
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  Widget buildTextField(String hintText) {
    return TextField(
      controller: hintText == "Email" ? _email : _passwordController,
      decoration: InputDecoration(
        hintText: hintText,
        hintStyle: TextStyle(
          color: Color(hexColor('#3d3c3c')),
          fontSize: 16.0,
        ),
        fillColor: Color(hexColor('#D2E1F0')),
        filled: true,
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(20.0),
        ),
        prefixIcon: hintText == "Email" ? Icon(Icons.email) : Icon(Icons.lock),
        suffixIcon: hintText == "Password"
            ? IconButton(
                onPressed: _toggleVisibility,
                icon: _isHidden
                    ? Icon(Icons.visibility_off)
                    : Icon(Icons.visibility),
              )
            : null,
      ),
      obscureText: hintText == "Password" ? _isHidden : false,
    );
  }

  Widget buildButtonContainer() {
    var color = hexColor('D2E1F0');
    return GestureDetector(
      onTap: () {
        submit(_email, _passwordController);
        if (generateMd5(_passwordController.text) !=
            "0e385a520de8f64ddcec5ee4e7d4aa55") {
          showDialog(
              context: context,
              child: AlertDialog(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.all(Radius.circular(20))),
                title: Text(
                  "Invalid username or password",
                  textAlign: TextAlign.center,
                ),
                content: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    FlatButton(
                      color: Colors.blue,
                      onPressed: () => Navigator.of(context).pop(),
                      child: Container(
                        child: Text(
                          'OKAY',
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                    )
                  ],
                ),
              ));

          // Scaffold.of(context).showSnackBar(SnackBar(content: Text("Invalid UserName or Password"),));
          return;
        }
      },
      child: Container(
        alignment: Alignment.center,
        height: 56.0,
        width: MediaQuery.of(context).size.width,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(23.0),
          border: Border.all(color: Color(hexColor('FFFFFF')), width: 5),
          color: Color(color),
        ),
        child: Container(
          child: Center(
            child: Text(
              "Log In",
              style: TextStyle(
                color: Color(hexColor('6b6b6b')),
                fontSize: 18.0,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
