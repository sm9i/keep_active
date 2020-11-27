import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class KeepActive {
  static const MethodChannel _channel = const MethodChannel('keep_active');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  //保持
  static void keep() {
    if (Platform.isAndroid) {
      _channel.invokeMethod('keep');
    }
  }

  //结束
  static void stop() {
    if (Platform.isAndroid) {
      _channel.invokeMethod('stop');
    }
  }
}
