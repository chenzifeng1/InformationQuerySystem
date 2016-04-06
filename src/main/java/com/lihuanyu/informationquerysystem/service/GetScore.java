package com.lihuanyu.informationquerysystem.service;

import com.google.gson.Gson;
import com.lihuanyu.informationquerysystem.GsonTemplate.ScoreJsonInfo;
import com.lihuanyu.informationquerysystem.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by skyADMIN on 16/3/30.
 */
@Service
public class GetScore {

    @Autowired
    private HttpSession httpSession;

    QueryUtil queryUtil = new QueryUtil();

    public ArrayList<ScoreJsonInfo.ScoreInfo> getScoreList(String message){
        Gson gson = new Gson();
        ScoreJsonInfo scoreJsonInfo = gson.fromJson(message, ScoreJsonInfo.class);
        return scoreJsonInfo.Table;
    }

    /**
     * 传入学号,获取成绩json数据
     * @return
     */
    public String getSorceFromWebservice() {
        String studentid = (String) httpSession.getAttribute("studentid");
        return queryUtil.findStudentScore(studentid);
    }

    public void showView(Model model, ArrayList<ScoreJsonInfo.ScoreInfo> scoreList) {
//        String xueqi = "0";
//        ArrayList<ArrayList<ScoreJsonInfo.ScoreInfo>> scoreLists = new ArrayList<ArrayList<ScoreJsonInfo.ScoreInfo>>();
//        ArrayList<ScoreJsonInfo.ScoreInfo> arrayList = new ArrayList<ScoreJsonInfo.ScoreInfo>();
//        int j = 0;
//        for (int i = 1; i <= scoreList.size(); i++){
//            if (!xueqi.equals(scoreList.get(i).XQM)){
//                xueqi = scoreList.get(i).XQM;
//
//            }
//        }

        scoreList = removeDuplicteScoreMessage(scoreList);

        model.addAttribute("scoreList",scoreList);
        String studentid = (String) httpSession.getAttribute("studentid");
        String realname = (String) httpSession.getAttribute("realname");
        model.addAttribute("studentid",studentid);
        model.addAttribute("studentname",realname);
    }

    public ArrayList<ScoreJsonInfo.ScoreInfo> removeDuplicteScoreMessage(ArrayList<ScoreJsonInfo.ScoreInfo> scoreInfo){
        LinkedHashSet<ScoreJsonInfo.ScoreInfo> s= new LinkedHashSet<ScoreJsonInfo.ScoreInfo>(scoreInfo);
        //s.addAll(scoreInfo);
        return new ArrayList<ScoreJsonInfo.ScoreInfo>(s);
    }
}
