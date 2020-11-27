#import "KeepActivePlugin.h"
#if __has_include(<keep_active/keep_active-Swift.h>)
#import <keep_active/keep_active-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "keep_active-Swift.h"
#endif

@implementation KeepActivePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftKeepActivePlugin registerWithRegistrar:registrar];
}
@end
