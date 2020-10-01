package statements;



import com.company.db.DbConnection;
import com.company.pojos.MemberStatement;
import com.company.pojos.Scheme;
import com.company.pojos.SchemeTransactions;
import com.company.utils.Utils;

import javax.rmi.CORBA.Util;
import java.sql.Connection;
import java.util.List;

public class Main {




    public static void main(String[] args) {

        System.out.println("Test SChema");


//        System.out.println(Utils.fetchMemberStatement());


        System.out.println(Utils.fetchSchemes());

        for (Scheme scheme:Utils.fetchSchemes()) {
            List<MemberStatement> memberStatements = Utils.fetchMemberStatement(scheme.getSchemeCode(), "032020");

            if(memberStatements.size()> 0) {

                SchemeTransactions schemeTransactions = new SchemeTransactions();

                Double runningB = 0.0;
                for (MemberStatement memberSts : memberStatements) {

                    Double shareCr = Double.parseDouble(memberSts.getShare_Cr());
                    Double shareDr = Double.parseDouble(memberSts.getShare_Dr());
                    Double value = shareCr - shareDr;
                    runningB += value;
                }


                schemeTransactions.setMemberStatements(memberStatements);
                schemeTransactions.setScheme(scheme);
                schemeTransactions.setRunningBalance(String.valueOf(runningB));
                System.out.println(schemeTransactions);
            }
        }



    }
}
