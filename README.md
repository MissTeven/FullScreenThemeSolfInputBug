# FullScreenThemeSolfInputBug
#解决全屏模式下的键盘遮盖问题
#在Theme主题中设置了<item name="android:windowFullscreen">true</item>之后，存在键盘遮盖BUG解决如下:
#step1.在activity的onCreate方法中在setContentView之后执行 AndroidBug5497Workaround.assistActivity(this)；
#step2.在布局xml文件里将跟布局换成com.example.fullscreenthemesoftinputbugutil.FullScreenContainerView；
#This's all.
