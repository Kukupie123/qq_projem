import 'package:flutter/material.dart';
import 'package:project_management_ui/kukukode/pm/ui/pages/sign/sign_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "QQ-ProMan",
        theme: ThemeData(
          primarySwatch: Colors.blueGrey,
        ),
        home: const PageSignScreen());
  }
}
