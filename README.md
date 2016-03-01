# Google Espresso with Spoon
## 1.Google Espresso

[How to Use Espresso v2.0 with Testdroid Cloud Devices](http://testdroid.com/news/how-to-use-espresso-v2-0-with-testdroid-cloud-devices)
### 1.Modify build.gradle
* Add 'testInstrumentationRunner"android.support.test.runner.AndroidJUnitRunner"' in defaultConfig
* Add packagingOptions to avoid Liscens conflict
* Add dependencies about Espresso

e.g : build.gradle
```sh
apply plugin: 'com.android.application'

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"

    defaultConfig {
        applicationId "com.philips.pins.espresso.demo"
        minSdkVersion 15
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    packagingOptions {
        exclude 'LICENSE.txt'
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.1.1'
    // Espresso 相关的引用
    androidTestCompile('com.android.support.test:runner:0.3') {
        exclude module: 'support-annotations'
    }
    androidTestCompile('com.android.support.test:rules:0.3') {
        exclude module: 'support-annotations'
    }
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2') {
        exclude module: 'support-annotations'
    }
    androidTestCompile('com.android.support.test.espresso:espresso-intents:2.2') {
        exclude module: 'support-annotations'
    }
}
```
## 2.Create Test Class
1. Create a @Rule to express test which activity
2. Create Annotation @Test method to test

#### p.s: Espresso is based on Juit, so it's okay to override setUp() and tearDown()
e.g: MainActivityTest.java
```sh
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testTextViewDisplay() {
        onView(withText("Hello World!")).check(ViewAssertions.matches(isDisplayed()));
    }

}
```
## References
[Google Github io ](https://google.github.io/android-testing-support-library/docs/espresso/index.html)

[Developer Training](http://developer.android.com/intl/zh-cn/training/testing/ui-testing/espresso-testing.html)

[Espresso](https://code.google.com/p/android-test-kit/wiki/Espresso)
## Some blogs
[自动化测试框架Espresso(一) 介绍和环境搭建](http://www.eoeandroid.com/thread-917959-1-1.html?_dsign=641adb77)

[自动化测试框架Espresso(二) 基本用法](http://www.eoeandroid.com/thread-917976-1-2.html?_dsign=80084669)

[自动化测试框架Espresso(三) 简单实例-登录场景自动化测试](http://www.eoeandroid.com/thread-917978-1-1.html?_dsign=6aab342a)

[Android Espresso(UI自动化测试)的搭建](http://www.w2bc.com/Article/40324)

Besides, it seems necessary to lock the screen when we use Espresso to test.
## 2.Spoon （一款辅助测试并生成测试报告的插件）
spoon的强大之处：
- 多机并行执行：同时在多台手机中执行自动化测试用例，以便在不同版本的Android操作系统中进行回归测试、兼容性测试
- 出错重试及出错截图：当用例执行未通过时，可以自动进行重跑并截图记录，以便减少因偶然因素导致用例执行未通过的情况
- 实时日志记录：对于测试执行过程应该能记录运行时的日志，以便详细发解测试执行情况
- 跨应用的能力：能够测试包含跨应用的诸多情况
- 生成Junit形式测试报告：生成详细的Junit形式的测试报告，可方便查看测试用例执行结果
- 代码覆盖率报告：生成代码覆盖率报告，以便进一步指导测试策略
- 持续快速反馈的能力：对于测试运行情况，应该要能够快速反馈
- 易于访问的报告：能够很方便地访问到测试报告详情
### How to use spoon
1. Download the latest runner JAR(This project contanins spoon-runner-1.3.2-jar-with-dependencies.jar)
2. Add the latest client JAR to your dependencies or download it
```sh
androidTestCompile 'com.squareup.spoon:spoon-client:1.3.2'
```
3. Run Spoon as a standalone tool with your application and instrumentation APKs.

cmd e.g:
```sh
java -jar spoon-runner-1.3.2-jar-with-dependencies.jar 
--apk D:\Espresso\app\build\outputs\apk\app-debug-unaligned.apk 
--test-apk D:\Espresso\app\build\outputs\apk\app-debug-androidTest-unaligned.apk 
--sdk C:\Users\310231492\AppData\Local\Android\sdk
```
4. Get test report in folder spoon-output

p.s: use Spoon.screenShot() to take screenshots, but do not forget to add the WRITE_EXTERNAL_STORAGE permission
```sh
Spoon.screenshot(activity, "initial_state");
/* Normal test code... */
Spoon.screenshot(activity, "after_login");
```
#### References
[Spoon Github](https://github.com/square/spoon)



