import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:keep_active/keep_active.dart';

void main() {
  const MethodChannel channel = MethodChannel('keep_active');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
  });
}
