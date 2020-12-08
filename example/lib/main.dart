import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:keep_active/keep_active.dart';
import 'package:notification_permissions/notification_permissions.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              RaisedButton(
                child: Text(
                  'check notification',
                  style: Theme.of(context).textTheme.caption,
                ),
                onPressed: () {
                  NotificationPermissions.getNotificationPermissionStatus().then((value) {
                    print(value);
                  });
                },
              ),
              RaisedButton(
                child: Text(
                  'keep',
                  style: Theme.of(context).textTheme.caption,
                ),
                onPressed: () {
                  KeepActive.keep();
                },
              ),
              SizedBox(height: 50),
              RaisedButton(
                child: Text(
                  'stop',
                  style: Theme.of(context).textTheme.caption,
                ),
                onPressed: () {
                  KeepActive.stop();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
