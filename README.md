# AppIntro
A Simple Library to create an App Intro

# How To
Add to your dependencies

       implementation 'com.github.philippgeppert:AppIntro:0.1.4'


Add to your activity

       List<String> heading = new ArrayList<>();
        heading.add("Title One");
        heading.add("Title Two");
        heading.add("Title Three");
        heading.add("Title Four");

        List<String> description = new ArrayList<>();
        description.add("Description One");
        description.add("Description Two");
        description.add("Description Three");
        description.add("Description Four");

        List<Integer> drawables = new ArrayList<>();
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);


        IntroBuilder introBuilder = new IntroBuilder();
        introBuilder.setHeadings(heading);
        introBuilder.setDescriptions(description);
        introBuilder.setDrawables(drawables);
        introBuilder.nextActivity(StartActivity.class);
        introBuilder.start(this);
