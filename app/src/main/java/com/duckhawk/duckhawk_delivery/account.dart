import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:project_duckhawk/main.dart';
class account extends StatefulWidget {
  @override
  _accountState createState() => _accountState();
}
String name="hello";
String s="hi";
/*
DocumentReference ref;
  getUserDoc() async {
  print("hi");
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final Firestore _firestore = Firestore.instance;

   FirebaseUser user = await _auth.currentUser();
  ref = _firestore.collection('users').document(user.uid);
  _firestore
      .collection("users").where("uid",isEqualTo:user.uid )
      .getDocuments()
      .then((QuerySnapshot snapshot) {
    //snapshot.documents.forEach((f) => print('${f.data}}'));
    snapshot.documents.forEach((f) => s='${f.data}');
    print("s is "+ s);
  });
  return s;
}*/
class _accountState extends State<account> {
  String g = "ji";

  FirebaseUser mCurrentUser;
  FirebaseAuth _auth;

  void initState() {
    super.initState();
    getUserDoc();
   // _getCurrentUser();

    //getUserDoc();

  }/*
  _getCurrentUser()async{
    _auth=FirebaseAuth.instance;
    mCurrentUser=await _auth.currentUser();
    print("Hello"+mCurrentUser.email.toString());
    name=mCurrentUser.email.toString();
    print(name+" 1");
    showDialog(
      context: context,
      builder: (_) => LogoutOverlay(),
    );
    @override
    Widget build(BuildContext context) {
      //getUserDoc();
      print(name+" 3");

      return Scaffold(
          appBar: AppBar(
            title: Text("Account"),
          ),
          body:
          Text(name+" 2")
      );
    }


  }*/

  DocumentReference ref;
  getUserDoc() async {
    print("hi");
    final FirebaseAuth _auth = FirebaseAuth.instance;
    final Firestore _firestore = Firestore.instance;

    FirebaseUser user = await _auth.currentUser();
    ref = _firestore.collection('users').document(user.uid);
    _firestore
        .collection("users").where("uid", isEqualTo: user.uid)
        .getDocuments()
        .then((QuerySnapshot snapshot) {
      //snapshot.documents.forEach((f) => print('${f.data}}'));
      snapshot.documents.forEach((f) => s = '${f.data}');
      //print(s.length);
      //print(s.split(',')[0]);
      //print(s.split(',')[1]);
      //print(s.split(',')[2]);
      //print(s.split(',')[3]);
      //

      //print("s is " + s);
      showDialog(
        context: context,
        builder: (_) => LogoutOverlay(),
      );
      //g = s.toString();
    });

  }

  @override

    Widget build(BuildContext context) {
      //getUserDoc();
      return Scaffold(
          appBar: AppBar(
            title: Text("My Account"),
          ),



      );
    }

}

class LogoutOverlay extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => LogoutOverlayState();
}

class LogoutOverlayState extends State<LogoutOverlay>
    with SingleTickerProviderStateMixin {
  AnimationController controller;
  Animation<double> scaleAnimation;

  /*@override
  void initState() {

    super.initState();

    controller =
        AnimationController(vsync: this, duration: Duration(milliseconds: 450));
    scaleAnimation =
        CurvedAnimation(parent: controller, curve: Curves.elasticInOut);

    controller.addListener(() {
      setState(() {});
    });

    controller.forward();
  }*/

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("My Account"),
      ),
      body:
      Container(
        child:
        Text(s.split(',')[1]+"\n"+s.split(',')[2]+"\n"+s.split(',')[3]+"\n"+s.split(',')[4]),
      ),

    );
    /*
    return Center(
      child: Material(
        color: Colors.transparent,
        child: ScaleTransition(
          scale: scaleAnimation,
          child: Container(
              margin: EdgeInsets.all(20.0),
              padding: EdgeInsets.all(15.0),
              height: double.infinity,
              width: double.infinity,

              decoration: ShapeDecoration(
                  color: Color.fromRGBO(41, 167, 77, 10),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15.0))),
              child: Column(
                children: <Widget>[
                  Expanded(
                      child: Padding(
                        padding: const EdgeInsets.only(
                            top: 30.0, left: 20.0, right: 20.0),
                        child: Text(
                          s.split(',')[1],
                          style: TextStyle(color: Colors.white, fontSize: 16.0),
                        ),
                      )),
                  Expanded(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Padding(
                            padding: const EdgeInsets.all(10.0),
                            child: ButtonTheme(
                                height: 35.0,
                                minWidth: 110.0,
                                child: RaisedButton(
                                  color: Colors.white,
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(5.0)),
                                  splashColor: Colors.white.withAlpha(40),
                                  child: Text(
                                    'Logout',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        color: Colors.green,
                                        fontWeight: FontWeight.bold,
                                        fontSize: 13.0),
                                  ),
                                  onPressed: () {
                                    setState(() {
                                      Route route = MaterialPageRoute(
                                          builder: (context) => HomePage(null));
                                      Navigator.pushReplacement(context, route);
                                    });
                                  },
                                )),
                          ),
                          Padding(
                              padding: const EdgeInsets.only(
                                  left: 20.0, right: 10.0, top: 10.0, bottom: 10.0),
                              child:  ButtonTheme(
                                  height: 35.0,
                                  minWidth: 110.0,
                                  child: RaisedButton(
                                    color: Colors.white,
                                    shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(5.0)),
                                    splashColor: Colors.white.withAlpha(40),
                                    child: Text(
                                      'Cancel',
                                      textAlign: TextAlign.center,
                                      style: TextStyle(
                                          color: Colors.green,
                                          fontWeight: FontWeight.bold,
                                          fontSize: 13.0),
                                    ),
                                    onPressed: () {
                                      setState(() {
                                        /* Route route = MaterialPageRoute(
                                          builder: (context) => LoginScreen());
                                      Navigator.pushReplacement(context, route);
                                   */ });
                                    },
                                  ))
                          ),
                        ],
                      ))
                ],
              )),
        ),
      ),
    );
  */}
}
