# Weather-SwensonHe

## This is an Assessment Task for to build a weather app. 
This app is built using kotlin, depending on MVVM design pattern.
### This app is able to run on Phones and Tablets
** This app includes layout for portrait and Landscape screens specially when screen width is bigger than 600dp.
### Used libraries:
1. lifecycle libraries for viewmodel, livedata, and savedstate.
2. Retrofit2.
3. OkHttp for logging interceptor purpose.
4. gson library for parsing JSON format.
5. Picasso and Glide are used for displaying images.
6. App support data binding.
### This app consists of:
1. MainActivity.kt displaying weather data.
2. There are 2 layouts, portrait and landscape to support devices with screen sizes +600dp. 
3. AppBarFragment.kt
4. SearchViewFragment.kt
5. WeatherViewModel.kt and WeatherViewModelFactory.kt files.
6. WeatherRepository.kt
7. There are 3 Interface files: OnArrowPressed, OnSearchPressed, and OnSearchResultSelected.
8. There are 9 files considered as data classes inside the *model* package.
9. RetrofitServiceInterface exists to help communicate to our webservice.
10. Util file help to utilize some resources in the device.
11. xml files: provides support for both portrait and landscape orientations.
12. fragment_search.xml is enhnaced and developed for appearing on both portrait and landscape with different behaviors.

## Screenshots of the app

![6_swen](https://user-images.githubusercontent.com/16711483/189366998-3c7ca58c-e060-48e1-b30d-05653a0c1bab.png)
![7_swen](https://user-images.githubusercontent.com/16711483/189367007-3431d9bb-4b17-4260-8723-46c14426ef32.png)
![8_swen](https://user-images.githubusercontent.com/16711483/189367010-b440a11f-b066-48fc-85b0-c2f8739f881e.png)
![1_swen](https://user-images.githubusercontent.com/16711483/189367021-235d9352-8095-4189-aa83-ba0710932180.png)
![2_swen](https://user-images.githubusercontent.com/16711483/189367034-03f74f46-3bdc-4b9d-8322-00115ed5a172.png)
![3_swen](https://user-images.githubusercontent.com/16711483/189367040-1fe538b6-892d-45e9-8fcc-812a31a22423.png)
![4_swen](https://user-images.githubusercontent.com/16711483/189367054-52c9922a-598e-4b2f-9fff-0a4e00f20324.png)
![5_swen](https://user-images.githubusercontent.com/16711483/189367058-17768244-d231-49a6-9ae9-d3171cb5b1b8.png)
