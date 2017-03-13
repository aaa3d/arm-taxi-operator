package ru.online76.arm_taxi_operator.ui_controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import ru.online76.arm_taxi_operator.database_controller.MainDatabase;
import ru.online76.arm_taxi_operator.model.Document;
import ru.online76.arm_taxi_operator.model.Orders;



@Controller
@RequestMapping(value = "/json")
public class ControllerJson {
    
    private class datagrid_data{
        
        private class _footer{
            public String CarName="итого 1";
            public String Time="итого 2";
        }
        private class work_orders_json{
            public String ID;
            public String AddrFrom;
            public String AddrTo;
            public String Phone;
            public String CarName;
            public String Time;
        }
        
        void LoadOrdersList(List<Orders> list){
            
            for (Orders order: list){
                work_orders_json result = new work_orders_json();
                result.ID = order.getId().toString();
                result.AddrFrom = order.GetAddrFromString();
                result.AddrTo = order.GetAddrToString();
                result.Phone = order.GetPhoneString();
                result.Time = order.GetTmString();
                result.CarName = order.GetCarString();
                rows.add(result);
            }
            total =rows.size();
            footer.clear();
            footer.add(new _footer());
            //footer.CarName="itogo 1";
        }
        
        

        
        public Integer total;
        public List<work_orders_json> rows = new  ArrayList<work_orders_json>();
        public   List<_footer>footer=new ArrayList<_footer>();
        
        
    }
   
    

	
	//
	@Autowired	private SessionFactory sessionFactory;
	@Autowired MainDatabase database;
	

    @Transactional
    @RequestMapping(value = "/organizations", method = RequestMethod.GET, headers="Accept=*/*",  produces="application/json;; charset=UTF-8")
    @ResponseBody
    public List work_orders_json(@RequestParam(required = false) Integer hidden_sort_column_index, @RequestParam(required = false) String hidden_sort_column_direction, ModelMap modelMap) {

        LinkedHashMap map = new LinkedHashMap();
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery(""+
"select id as id, Name as text, '' as 'desc' from organizations\n" +
"where AgreementStatus=1\n" +
"order by Name");
        
/*                
        Query query = session.createSQLQuery(
"select s.stock_code from stock s where s.stock_code = :stockCode")
.setParameter("stockCode", "7277");
        */
        List result = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        //List<Orders> list = database.getgetWorkOrdersList(getJournalTableFieldName(hidden_sort_column_index), hidden_sort_column_direction);
       // List<work_orders_json> result_list = new  ArrayList<work_orders_json>();
        
       // datagrid_data data = new datagrid_data();
        //data.LoadOrdersList(list);
        
        /*
        for (Order order: list){
            work_orders_json result = new work_orders_json();
            result.ID = order.getID().toString();
            result.AddrFrom = order.GetAddrFromString();
            result.AddrTo = order.GetAddrToString();
            result.Phone = order.GetPhoneString();
            result.Time = order.GetTmString();
            result.CarName = order.GetCarString();
            
            result_list.add(result);
        }*/
     
        
        return result;
    }
    
    
    
    

}
