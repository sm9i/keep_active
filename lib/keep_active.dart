import 'dart:async';

import 'package:flutter/services.dart';

class KeepActive {
  static const MethodChannel _channel = const MethodChannel('keep_active');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  //保持
  static void keep() => _channel.invokeMethod('keep');

  //结束
  static void stop() => _channel.invokeMethod('stop');
}
