// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class PageSignScreen extends StatefulWidget {
  const PageSignScreen({Key? key}) : super(key: key);

  @override
  _PageSignScreenState createState() => _PageSignScreenState();
}

class _PageSignScreenState extends State<PageSignScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(hintText: "email"),
            ),
            TextField(
              decoration: InputDecoration(hintText: "password"),
            ),
            TextButton(onPressed: testPing, child: Text("TEST backend "))
          ],
        ),
      ),
    );
  }

  void testPing() async {
    final response = await http.get(Uri.parse('http://localhost:8080/test'));

    print(response.statusCode);
    print(response.body);
  }
}
