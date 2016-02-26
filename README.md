# Google Espresso
## 1.Modify build.gradle
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

