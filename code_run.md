编写代码和系统运行介绍

# 一、开发环境
开发环境推荐使用 Eclipse Photon Release (4.8.0)， eclipse.ini 添加配置：

```
-vm                                                  // 指定java的参数
D:/programs/Java/jdk1.8.0_101/bin/javaw.exe          // 指定java的路径
-Xms512m                                             // JVM参数
-Xmx2048m                                            // JVM参数
-Dfile.encoding=utf-8                                // 指定默认编码
```

## 1.1、 安装STS插件
STS插件安装后 Ctrl + Shift + O 快捷键 需要重新配置：

preference -> general -> keys, 找到Organize Imports ,然后 在 “WHEN”里面 选择 Editing Java Source

## 1.2、Preferences
菜单Windows > Preferences 显示Preferences窗口

1、字体

在左上角的输入框输入font 即可快速选择colors and fonts，中间区域选择Basic > Text Font，双击或者点击右侧的Edit，显示字段对话框, 字体选择Courier New，大小选择12或者小四（根据个人喜好自行调整）

如果没有Courier New字体，点击左下角的显示更多字体，右键Courier New字体，点击显示即可激活。

如果Courier New字体仍然不好看，可用 Yahei Consolas Hybrid字体 https://download.csdn.net/download/bowei026/10749413

2、UTF-8

General > Content Types > Text > Java Properties File,将界面底部Default encoding改为UTF-8，点击Update;

Java Properties File > Spring Properties File将界面底部Default encoding改为UTF-8，点击Update。

3、tab配置为空格

General > Editors > Text Editors，Displayed tab width设置为4，勾选Insert spaces for tabs。

Java > Code Style > Formatter，New或者Edit一个profile，Indentation > Tab policy选择Spaces only, Indentaion size 和 Tab size都设置为4

XML > XML Files > Editors, 选中Indent using spaces, Indentation size 设置为4

# 二、运行系统

