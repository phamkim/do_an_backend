class UserModel {
  String? userId;
  String? email;
  String? name;
  String? photoURL;

  UserModel({this.userId, this.email, this.name, this.photoURL});

  UserModel.fromJson(Map<String, dynamic> json) {
    userId = json['userId'];
    email = json['email'];
    name = json['name'];
    photoURL = json['photoURL'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['userId'] = this.userId;
    data['email'] = this.email;
    data['name'] = this.name;
    data['photoURL'] = this.photoURL;
    return data;
  }
}
