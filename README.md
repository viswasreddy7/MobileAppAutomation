# Setting Up Development Environment on Mac
1. Install [HomeBrew](https://brew.sh/)
```
 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

2. Add the following lines to ~/.bash_profile and/or ~/.zhsrc
```
export PATH=/opt/homebrew/bin:$PATH
export PATH=/opt/homebrew/sbin:$PATH
```

3. Install Maven
```
brew install maven
mvn --version
```

4. Install Node
```
brew install node
```

5. Install carthage (For iOS)
```
brew install carthage
```

6. Install Appium
```
npm install -g appium
npm install -g appium-doctor
```

7. Install Appium drivers
```
appium driver install uiautomator2
appium driver install xcuitest 
```

8. Setup appium using appium-doctor guidance. Run the following command, and follow the instructions to install all dependencies
```
appium-doctor
```

# Steps 9 thru 13 are for Setting up Android Studio and Android Emulator
9. Download Android Studio for Intel/M1 Mac from [link](https://developer.android.com/studio)
10. Install Android Studio [link](https://developer.android.com/studio/install)
11. Download and Install Platform Tools SDK [link](https://developer.android.com/tools/releases/platform-tools)
12. Create new Android Virtual Device (AVD) Emulator [link](https://developer.android.com/studio/run/managing-avds)
13. Start the Android emulator from Android Studio [link](https://developer.android.com/studio/run/emulator#runningapp)

# Following steps 14 to 19 are for setting up adb
14. Download Android platform-tools [link](https://dl.google.com/android/repository/platform-tools-latest-darwin.zip)
15. Extract the zip file
16. Open the platform-tools folder
17. Press command key, right-click on adb, and click open.
18. Confirm opening adb
19. Add the following lines to ~/.bash_profile and/or ~/.zhsrc
```
export PATH=<path to platform-tools directory>:$PATH
export Path=<path to android tools directory>:$PATH
export Path=<path to android tools bin directory>:$PATH
```
# Setup JAVA_HOME
20. install jdk11 from [oracle](https://www.oracle.com/ca-en/java/technologies/downloads/#java11) Add the following lines to path of ~/.bash_profile and/or ~/.zhsrc like this JAVA_HOME path
```
export JAVA_HOME=JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-11.jdk/Contents/Home
export PATH=$JAVA_HOME:$PATH
```

# Install XCode for iOS
21. Install XCode  from app store [link](https://apps.apple.com/us/app/xcode/id497799835?mt=12)

# Install Appium Inspector
22. Download appropriate Appium inspector version from [here](https://github.com/appium/appium-inspector/releases)
23. Install Appium Inspector [link](https://github.com/appium/appium-inspector#installing-on-macos)

# Download IntelliJ
24. Download IntelliJ from here [link](https://www.jetbrains.com/idea/download)

# Checkout Repository
25. Checkout this repo locally
```
git clone ssh://git@github.com:Thrillworks/tw-appium.git
```

# Setup project in IntelliJ
26. Open IntelliJ
27. Open project
28. run mvn compile from terminal of intellij or click on build once intellij is picked as maven project

# Running Tests using Android Emulator
29. In IntelliJ, open [test.properties] and update the APK File location in app.fileLocation, android.deviceName(you get it when you run adb devices in terminal), app.isAndroid as true, android.deviceVersion
30. Open Android Studio and start Emulator (Already created in previous step no.10 and 11)
31. Right click on testng.xml.
32. Click Run

# Running Tests using iOS Simulator
33. In IntelliJ, open [test.properties] and update the .app File location in app.fileLocation, ios.deviceName(you get it from xcode devices and simulators), app.isAndroid as false, ios.deviceVersion, and ios.deviceUUID
34. Open simulators and run device of your choice.
35. Right click on testng.xml.
36. Click Run
