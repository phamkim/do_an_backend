import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../core/viewmodels/auth_view_model.dart';
import './login_view.dart';
import 'package:lottie/lottie.dart';
import '../constance.dart';

class RegisterView extends GetWidget<AuthViewModel> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  RegisterView({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final primaryColor = Theme.of(context).primaryColor;
    const borderRadius = BorderRadius.all(Radius.circular(kBorder));
    final border = OutlineInputBorder(
      borderRadius: borderRadius,
      borderSide: BorderSide(color: primaryColor),
    );
    double width = context.width;
    return Scaffold(
      appBar: AppBar(
        leading: GestureDetector(
          onTap: () {
            Get.off(const LoginView());
          },
          child: const Icon(Icons.arrow_back),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(kPadding),
        child: ListView(
          children: [
            Form(
              key: _formKey,
              child: Column(
                children: [
                  SizedBox(
                    height: width * 0.7,
                    child: Lottie.network(
                        "https://assets6.lottiefiles.com/packages/lf20_u8o7BL.json"),
                  ),
                  const SizedBox(
                    height: 30.0,
                  ),
                  TextFormField(
                    cursorColor: primaryColor,
                    keyboardType: TextInputType.emailAddress,
                    textInputAction: TextInputAction.next,
                    decoration: InputDecoration(
                      border: border,
                      focusedBorder: border,
                      hintText: "your name",
                      prefixIcon: Padding(
                        padding: const EdgeInsets.all(kPadding),
                        child: Icon(
                          Icons.person,
                          color: primaryColor,
                        ),
                      ),
                    ),
                    onSaved: (value) {

                    },
                    validator: (value) {
                      if (value == null) {
                        return 'Please enter name';
                      }
                      return null;
                    },
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 20.0),
                    child: TextFormField(
                      cursorColor: primaryColor,
                      keyboardType: TextInputType.emailAddress,
                      textInputAction: TextInputAction.next,
                      decoration: InputDecoration(
                        border: border,
                        focusedBorder: border,
                        hintText: "your email",
                        prefixIcon: Padding(
                          padding: const EdgeInsets.all(kPadding),
                          child: Icon(
                            Icons.person,
                            color: primaryColor,
                          ),
                        ),
                      ),
                      onSaved: (value) {

                      },
                      validator: (value) {
                        if (value == null) {
                          return 'Please enter email';
                        }
                        return null;
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 20.0),
                    child: TextFormField(
                      textInputAction: TextInputAction.done,
                      obscureText: true,
                      decoration: InputDecoration(
                        border: border,
                        focusedBorder: border,
                        hintText: "Your password",
                        prefixIcon: Padding(
                          padding: const EdgeInsets.all(kPadding),
                          child: Icon(
                            Icons.lock,
                            color: primaryColor,
                          ),
                        ),
                        suffixIcon: Padding(
                          padding: const EdgeInsets.all(kPadding),
                          child: IconButton(
                            icon: const Icon(Icons.visibility_off),
                            color: primaryColor,
                            onPressed: () {},
                          ),
                        ),
                      ),
                      onSaved: (value) {

                      },
                      validator: (value) {
                        if (value == null) {
                          return 'Please enter password';
                        }
                        return null;
                      },
                    ),
                  ),
                  const SizedBox(height: 20.0),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      minimumSize: Size(width, btnHeight),
                    ),
                    onPressed: () {
                      _formKey.currentState?.save();
                      if (_formKey.currentState!.validate()) {

                      }
                    },
                    child: const Text(
                      "SIGN UP",
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}