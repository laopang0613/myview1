package com.example.pangyinglei.myview1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pangyinglei on 2017/1/10.
 */

public class MyBook implements Parcelable {

    private String name;
    private String path;
    //当前章节索引
    private int currChapterIndx =0;
    private int charTotalCount;
    private List<Chapter> chapterList = new ArrayList<Chapter>();

    private List<BookMark> bookMarkList = new ArrayList<BookMark>();
    private int currBookMarkIndx;

    //缓存的章节索引,默认容量为2
    private List<Integer> cacheChapterNums = new ArrayList<Integer>(2);

    public MyBook() {
        //initBook();
    }

    public MyBook(Parcel in){
        name = in.readString();
        currChapterIndx = in.readInt();
        charTotalCount = in.readInt();
        path = in.readString();
        in.readTypedList(chapterList,Chapter.CREATOR);
        //in.readTypedList(bookMarkList,);
        in.readList(cacheChapterNums,List.class.getClassLoader());
    }

    private void initBook(){
        cacheChapterNums.add(0);
        cacheChapterNums.add(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurrChapterIndx() {
        return currChapterIndx;
    }

    public void setCurrChapterIndx(int currChapterIndx) {
        this.currChapterIndx = currChapterIndx;
    }

    public int getCharTotalCount() {
        return charTotalCount;
    }

    public void setCharTotalCount(int charTotalCount) {
        this.charTotalCount = charTotalCount;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public Chapter getCurrChapter(){
        return this.getChapterList().get(this.getCurrChapterIndx());
    }

    public List<BookMark> getBookMarkList() {
        return bookMarkList;
    }

    public void setBookMarkList(List<BookMark> bookMarkList) {
        this.bookMarkList = bookMarkList;
    }

    public int getCurrBookMarkIndx() {
        return currBookMarkIndx;
    }

    public void setCurrBookMarkIndx(int currBookMarkIndx) {
        this.currBookMarkIndx = currBookMarkIndx;
    }

    public BookMark getCurrBookMark(){
        return this.getBookMarkList().get(this.currBookMarkIndx);
    }

    public Chapter getPrevChapter(){
        if(this.getCurrChapterIndx() == 0){
            return null;
        }
        else{
            return this.getChapterList().get(this.getCurrChapterIndx() - 1);
        }
    }

    public Chapter getNextChapter(){
        if(this.getCurrChapterIndx() == this.getChapterList().size() - 1) {
            return null;
        }
        else{
            return this.getChapterList().get(this.getCurrChapterIndx() + 1);
        }
    }

    public List<Integer> getCacheChapterNums() {
        return cacheChapterNums;
    }

    public void setCacheChapterNums(List<Integer> cacheChapterNums) {
        this.cacheChapterNums = cacheChapterNums;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(currChapterIndx);
        dest.writeInt(this.charTotalCount);
        dest.writeString(path);
        dest.writeTypedList(chapterList);
        dest.writeList(cacheChapterNums);
    }

    public final static Parcelable.Creator<MyBook> CREATOR = new Parcelable.Creator<MyBook>(){
        @Override
        public MyBook createFromParcel(Parcel source) {
            return new MyBook(source);
        }

        @Override
        public MyBook[] newArray(int size) {
            return new MyBook[size];
        }
    };
}
