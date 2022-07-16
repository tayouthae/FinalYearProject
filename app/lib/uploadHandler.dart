import 'dart:io';

import 'package:app/variables.dart';
import 'package:clipboard_manager/clipboard_manager.dart';
import 'package:dio/dio.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;

class UploadHandler extends StatefulWidget {
  @override
  _UploadHandlerState createState() => _UploadHandlerState();
}

class _UploadHandlerState extends State<UploadHandler> {
  File file;
  bool _fileVisibility = false;
  var dio = Dio();
  bool visible = false;
  String data = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Uploading"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Visibility(
              visible: _fileVisibility,
              child: Text(file?.path?.split("/")?.last ?? ''),
            ),
            SizedBox(height: 30),
            RaisedButton(
              child: Text("Choose File"),
              onPressed: () async {
                file = await FilePicker.getFile(type: FileType.any);
                print("File path: ${file.absolute}");
                setState(() {
                  if (file != null) {
                    _fileVisibility = true;
                  } else {
                    _fileVisibility = false;
                  }
                });
              },
            ),
            SizedBox(height: 20),
            RaisedButton(
              child: Text("Upload"),
              onPressed: () async {
                var formData = FormData.fromMap({
                  'file': await MultipartFile.fromBytes(file.readAsBytesSync(),
                      filename: 'manual.pdf')
                });
                var response = await dio
                    .post(url +':8080/ipfsStore', data: formData);
                if (response.statusCode == 200) {
                  print('Uploaded!');
                  Fluttertoast.showToast(
                    msg: "File Uploaded",
                    toastLength: Toast.LENGTH_SHORT,
                    gravity: ToastGravity.BOTTOM,
                    timeInSecForIosWeb: 1,
                    backgroundColor: Colors.grey,
                    textColor: Colors.white,
                    fontSize: 16.0,
                  );
                  data = response.data;

                  print(data);
                  setState(() {
                    visible = true;
                  });
                } else {
                  print(response.statusCode);
                }
              },
            ),
            SizedBox(height: 30),
            Visibility(
              visible: visible,
              child: Column(
                children: [
                  SelectableText("https://ipfs.io/ipfs/" + data ?? ''),
                  SizedBox(height: 20),
                  IconButton(
                    icon: Icon(Icons.content_copy),
                    onPressed: () {
                      ClipboardManager.copyToClipBoard(
                              "https://ipfs.io/ipfs/" + data)
                          .then(
                        (result) {
                          Fluttertoast.showToast(
                            msg: "Copied to clipboard",
                            toastLength: Toast.LENGTH_SHORT,
                            gravity: ToastGravity.BOTTOM,
                            timeInSecForIosWeb: 1,
                            backgroundColor: Colors.grey,
                            textColor: Colors.white,
                            fontSize: 16.0,
                          );
                        },
                      );
                    },
                  ),
                  // SizedBox(height: 20),
                  Text(
                    "Copy the text above and send a message to your course instructor",
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
