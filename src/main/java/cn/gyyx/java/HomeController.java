package cn.gyyx.java;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaio.uuid.UUID;

import cn.gyyx.java.entity.User;
import cn.gyyx.java.entity.GameInfo;
import cn.gyyx.java.entity.ServerInfo;
import cn.gyyx.java.persistence.GameDao;
import cn.gyyx.java.persistence.ServerDao;
import cn.gyyx.java.persistence.UserServiceDao;
import cn.gyyx.java.persistence.MemcachedDao;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	private GameDao gameDao;
	private ServerDao serverDao;
	private UserServiceDao userService;

	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpServletRequest request) {
		userService = new UserServiceDao();
		String userName = request.getParameter("userId").toString();
		String passWord = request.getParameter("passwd").toString();

		boolean loginStatus = userService.validLogin(userName, passWord);
		if (loginStatus) {
			return "redirect:/home";
		}
		return "redirect:/";
	}
*/
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		gameDao = new GameDao();
		List<GameInfo> list = gameDao.queryList();
		serverDao = new ServerDao();
		List<ServerInfo> lists = serverDao.queryListById(1);
		model.addAttribute("serverList", lists);
		model.addAttribute("gameList", list);
		MemcachedDao.getInstance().SetInfo(lists.get(0));
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String home(ServerInfo model,HttpServletRequest request) {
		String serverName = request.getParameter("serverName").toString();
		serverDao = new ServerDao();
		serverDao.UpdateServerById(serverName);
		return "home";
	}

	/*
	 * 根据游戏ID获取区服列表
	 */
	@RequestMapping(value = "/getServerList", method = RequestMethod.GET)
	public @ResponseBody List<ServerInfo> getServerList(
			@RequestParam("gameId") int gameId) {
		serverDao = new ServerDao();
		List<ServerInfo> list = serverDao.queryListByGameId(gameId);
		return list;
	}
	
	/*
	 * 根据游戏ID获取区服列表
	 */
	@RequestMapping(value = "/getServerInfoList", method = RequestMethod.GET)
	public @ResponseBody List<ServerInfo> getServerInfoList(
			@RequestParam("serverId") int serverId) {
		serverDao = new ServerDao();
		List<ServerInfo> list = serverDao.queryListById(serverId);
		return list;
	}
	
	@RequestMapping(value = "/memcahe", method = RequestMethod.GET)
	public String memcahe(Model model) {
		String name=MemcachedDao.getInstance().GetInfo("1");
		model.addAttribute("name", name);
		return "memcahe";
	}
}
