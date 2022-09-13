

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import './auth/login_view.dart';

class HomeView extends StatelessWidget {
  HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Home screen"),
      ),
      body: Center(
        child: TextButton(
          child: const Text("Logout"),
          onPressed: (){
            Get.offAll( const LoginView());
          },
        ),
      ),
    );
  }
}
