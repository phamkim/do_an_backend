

import 'package:flutter/material.dart';
import 'package:fontend/views/constance.dart';
import 'package:get/get.dart';
import './auth/login_view.dart';

class HomeView extends StatelessWidget {
  HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: const EdgeInsets.only(top: 100.0, left: 20.0, right: 20.0),
        child: Column(
          children: [

            Container(
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(kBorder),
                color: Theme.of(context).cardColor
              ),
              child: TextFormField(
                cursorColor: Theme.of(context).primaryColor,
                decoration: const InputDecoration(
                  border: InputBorder.none,
                  prefixIcon: Icon(
                    Icons.search_outlined,
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
