package admin.controller.Facility;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.model.FaciBean;
import admin.model.FaciDao;

@Controller
public class FaciInsertController {
	
	private final String command = "/Faciinsert.admin";
	private final String getPage = "/FaciInsertForm";
	private final String gotoPage = "redirect:/Facilist.admin";
	
	@Autowired
	private FaciDao faciDao;

	@RequestMapping(value= command,method=RequestMethod.GET)
	public String insert2(Model model) {
		
		List<FaciBean> lists = faciDao.getFacGroupList();
		model.addAttribute("lists", lists);
		
		System.out.println("lists.size : " + lists.size());
		
		return getPage;
	}

	@RequestMapping(value= command,method=RequestMethod.POST)
	public ModelAndView Faciinsert(@ModelAttribute("faci") @Valid FaciBean bean, 	
							BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = faciDao.insertFaci(bean);
		System.out.print("cnt:"+cnt);
		mav.setViewName(gotoPage);
		return mav;

	}
}
