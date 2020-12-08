import 'dart:async';
import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class KeepActive {
  static const MethodChannel _channel = const MethodChannel('keep_active');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  //保持
  static Future<void> keep() async {
    if (Platform.isAndroid) {
      final result = await _channel.invokeMethod('keep');
      debugPrint('$result');
    }
  }

  //结束
  static Future<void> stop() async {
    if (Platform.isAndroid) {
      final result = await _channel.invokeMethod('stop');
      debugPrint('$result');
    }
  }
}
