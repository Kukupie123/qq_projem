// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_awesome_buttons/flutter_awesome_buttons.dart';
import 'package:project_management_ui/kukukode/pm/ui/pages/sign/glass/GlassBox.dart';

class PageSignScreen extends StatelessWidget {
  const PageSignScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Stack(
            children: [
              Center(
                child: Stack(
                  alignment: Alignment.bottomCenter,
                  children: [
                    Container(
                      width: MediaQuery.of(context).size.width,
                      height: MediaQuery.of(context).size.height,
                      decoration: BoxDecoration(
                        gradient: LinearGradient(
                          colors: [
                            Colors.red.shade400,
                            Colors.pink,
                            Colors.pinkAccent.shade400,
                          ],
                          begin: Alignment.topCenter,
                          end: Alignment.bottomCenter,
                        ),
                      ),
                    ),

                    Padding(
                        padding: const EdgeInsets.only(bottom: 620),
                        child: Column(
                          children: [

                            Image.asset(
                              'images/logo',
                              fit: BoxFit.cover,
                              alignment: Alignment.topCenter,
                              color: Colors.white,
                              scale: 1.3,
                            ),
                          ],
                        )),
                    Padding(
                      padding: const EdgeInsets.all(25.0),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [

                          Padding(
                            padding: const EdgeInsets.only(bottom: 100),
                            child:
                                DarkButtton(title: "title", onPressed: () {}),
                          ),

                          Text(
                            'Handling projects has never been easier and fun!',
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 16,
                              fontWeight: FontWeight.w400,
                            ),
                            textAlign: TextAlign.center,
                          ),


                          SizedBox(
                            height: 20,
                          ),
                          SizedBox(
                            height: 15,
                          ),
                          SizedBox(
                            height: 15,
                          ),
                          SizedBox(
                            height: 30,
                          ),
                          Text(
                            'Trouble logging in?',
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.w700,
                            ),
                          ),
                          SizedBox(
                            height: 10,
                          ),
                        ],
                      ),
                    ),

                  ],

                ),
              ),
              GlassBox(height: 123, width: 123),
            ],
          ),

        ],
      ),
    );
  }
}
