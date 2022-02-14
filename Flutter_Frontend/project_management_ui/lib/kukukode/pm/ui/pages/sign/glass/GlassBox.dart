// ignore_for_file: avoid_unnecessary_containers, prefer_const_literals_to_create_immutables, prefer_const_constructors

import 'dart:ui';

import 'package:flutter/material.dart';

class GlassBox extends StatefulWidget {
  final double height;
  final double width;

  const GlassBox({Key? key, required this.height, required this.width})
      : super(key: key);

  @override
  _GlassBoxState createState() => _GlassBoxState();
}

class _GlassBoxState extends State<GlassBox> {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: widget.width,
      height: widget.height,
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 10.0, sigmaY: 0.0),
        child: Container(
          //you can change opacity with color here(I used black) for background.
          decoration: BoxDecoration(
            color: Colors.black.withOpacity(0.1),
          ),
          child: Text("SUP"),
        ),
      ),
    );
  }

  Widget generateBlurredImage() {
    return Container(
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 3.0, sigmaY: 3.0),
        child: Container(
          //you can change opacity with color here(I used black) for background.
          decoration: BoxDecoration(
            color: Colors.black.withOpacity(0.1),
          ),
        ),
      ),
    );
  }
}
