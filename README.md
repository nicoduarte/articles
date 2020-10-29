# Hacker News App
This is an Android application to visualize articles using data from Hacker News.

## Pre-requisites
* Android Studio 4.1

## Features
This app contains two screens:

| Post list  | Post detail |
:---------:|:--------:|:-------:|
| ![](https://github.com/nicoduarte/articles/blob/main/art/img_list.png) | ![](https://github.com/nicoduarte/articles/blob/main/art/img_detail.png) |

Offline support using Room and RxJava.

## Architecture
Uses ViewModels and LiveData from Architecture Components with an MVVM architecture.

![](https://github.com/nicoduarte/articles/blob/main/art/img_architecture.png)

Remote data: Backend communication using Retrofit and RxJava2.
