# CodeHub-DicodingBFAA

Submission Github User App untuk kelas Belajar Fundamental Aplikasi Android Dicoding

## Preview

<img src="https://raw.githubusercontent.com/mikirinkode/CodeHub-DicodingBFAA/master/assets/CodeHub_Dark.png" alt="CodeHub Dark Theme Preview"/>

<img src="https://raw.githubusercontent.com/mikirinkode/CodeHub-DicodingBFAA/master/assets/CodeHub_Light.png" alt="CodeHub Light Theme Preview"/>

## If you want to clone this app
you need to update github token in Constatns.kt class at com.mikirinkode.codehub.utils

```
class Constants {
    companion object {
        const val GITHUB_TOKEN = "put_your_github_token_here"
        
        ...
    }
}
```

<br>

### Secure the Token 🛡️
if you want to secure your token, you can put it in local.properties then rebuild project.
example:

```
GITHUB_TOKEN = ghp_111111111111111111
```

then uncomment this 3 line to access your token from local.properties
```
android {
    ...
    
    defaultConfig {
          ...

//        Properties properties = new Properties()
//        properties.load(project.rootProject.file('local.properties').newDataInputStream())
//
//        buildConfigField("String", "GITHUB_TOKEN", "\"${properties.getProperty('GITHUB_TOKEN')}\"")
    }

    ...
}

```

access it in Constants.kt class
```
class Constants {
    companion object {
        const val GITHUB_TOKEN = BuildConfig.GITHUB_TOKEN
        
        ...
    }
}
```
