
/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/27 20:24
 * Description: 
 */

//printf "\033c"


class Train{

    private int rate        //帧运行速度，单位 milis
    private int border   //动画长度

    private List<Frame> frameList = new ArrayList<>()

    Train(String dir,int rate,int border){
        this.rate = rate
        this.border = border
        def files = new File(dir)

        //读取素材
        for(def file in files.listFiles()){
            Frame frame = new Frame(file)
            frameList.add(frame)
        }


        //帧组调整
        for(i in frameList.indices)
            frameList.get(i).go(i);

    }

    void gogogo(){
        printf "\033c"   //清屏
        while(true){
            //一个帧组循环
            for(frame in frameList){
				printf "\033c"   //清屏
                frame.go(frameList.size());
                frame.print();
                Thread.sleep(rate);
            }
        }

    }


    //帧对象
    class Frame{
        List<String> strList = new ArrayList<>()

        Frame(File file){
            def reader = new BufferedReader(new InputStreamReader(new FileInputStream((file)),'utf-8'))
            def str = ""
            while((str = reader.readLine()) != null){
                def space = ""
                for(i in 1..border-str.length())
                    space += " "
                strList.add(str+space)
            }
            reader.close()


        }

        //帧移动更变
        void go(int step){
            for(i in strList.indices){
                def str = strList.get(i);
                def loopStr = str.substring(str.length()-step,str.length())
                str = loopStr + str.substring(0,str.length()-step)
                strList.set(i,str)
            }
        }

        //帧打印
        void print(){
            for(str in strList)
                println(str)
        }

    }


    static void main(String[] args){
        new Train('./material',90,200).gogogo();
    }
}


