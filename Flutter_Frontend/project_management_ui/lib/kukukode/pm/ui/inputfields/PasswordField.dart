// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class PasswordField extends StatefulWidget {
  const PasswordField({Key? key}) : super(key: key);

  @override
  _PasswordFieldState createState() => _PasswordFieldState();
}

class _PasswordFieldState extends State<PasswordField> {
  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.all(Radius.circular(20)),
      child: Container(
        color: Colors.blueGrey,
        child: Padding(
          padding: const EdgeInsets.only(left: 10, right: 10),
          child: TextField(
            decoration: InputDecoration(
                helperText: "Your Password",
                hintStyle: TextStyle(color: Colors.black),
                label: Text("Password"),
                icon: Icon(Icons.password)),
            expands: false,
            enableSuggestions: true,
            showCursor: true,
            keyboardType: TextInputType.emailAddress,
          ),
        ),
      ),
    );
  }
}
