// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class EmailInputField extends StatefulWidget {
  const EmailInputField({Key? key}) : super(key: key);

  @override
  _EmailInputFieldState createState() => _EmailInputFieldState();
}

class _EmailInputFieldState extends State<EmailInputField> {
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
                helperText: "Your E-mail",
                hintStyle: TextStyle(color: Colors.black),
                label: Text("Email"),
                icon: Icon(Icons.email)),
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
