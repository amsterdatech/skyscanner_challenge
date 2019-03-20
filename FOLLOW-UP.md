# Implementation:

### Q) What libraries did you add to the app? What are they used for?

---

# General:

### Q) How long did you spend on the test? 

## 1- 3 nights straight

### Q) If you had more time, what further improvements or new features would you add?

## 1-  Try to fix some problems regarding androidX and dagger 2.16 which not allowed me to use ViewModel and LiveData due to some problems. 
## 2 -  would extract text ,color, margin  and size attributes from all views to custom styles which could be more organised in themes (Best Practice.
## 3 - Try to implement effective throtthling regarding API_LIMIT's
## 4 - Try to create some kind of local cache to avoid request data while it's still fresh
## 5 - Create compound views in order to hold view logic like Error, Empty and Loading states
## 6 - Add Fragment support in order to use this screen inside ViewPager/BottomNavigation
## 7 - Try to implement Search as per Skyscanner official app


### Q) Which parts are you most proud of? And why?

## 1- The architecture, I followed clean one and became easier to change some implementation in the future. I used it for practice, since in most basic projects/challenges it can be some kind of overengineering.
## 2 - I did the challenge with one objective in mind, create a  boilerplate app ,in order   that's  I'll be able to  test more easily some tools, libraries and architectures in the future.

### Q) Which parts did you spend the most time with? What did you find most difficult?

## 1 - Server return json to flat and the hierarchy of model is too nested creating two main difficulties:
   * Understanding of model and translate to UI data
   * Data model  which leads client to do some pre-processing instead of simple parsing(BFF)
2 - Problems with Dagger and Android X in order to inject ViewModel and ViewModelFactory
3 - I took some time , when I realised that I need to encode the form in a map before use FormUrlEnconded annotation in Retrofit X)

### Q) How did you find the test overall? If you have any suggestions on how we can improve the test or our API, we'd love to hear them.

## 1 - The test has a great proposal, in order to verify the skills but also the way of thinking of the developer. Most of times, when I was recruiting for my team, I spent a lot of time analysing  and looking for a good mind fit. I think that's there are some things to improve, but you should hire me before , thanks in advance for the opportunity.

