
"""
from pyvirtualdisplay import Display
from selenium import webdriver
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary

display=Display(visible=0, size=(320, 240)).start()

driver = webdriver.PhantomJS()
#driver= webdriver.Chrome('/usr/bin/google-chrome')
driver.get("www.google.com")
html_source = driver.page_source
print (html_source)
driver.close()
"""


import re
from urllib.parse import urlparse
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium import webdriver
from bs4 import BeautifulSoup
from time import sleep

link = 'http://www.amazon.in/gp/goldbox/ref=nav_cs_gb'



from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class wait_for_price(object):
    def __init__(self, locator):
        self.locator = locator

    def __call__(self, driver):
        try :
            element_text = EC._find_element(driver, self.locator).text.strip()
            return element_text != "0,00"
        except StaleElementReferenceException:
            return False


class AmzonScraper(object):
	def __init__(self):
		caps = webdriver.DesiredCapabilities.PHANTOMJS
		caps["phantomjs.page.settings.userAgent"] = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/53 (KHTML, like Gecko) Chrome/15.0.87"

		self.driver = webdriver.PhantomJS(desired_capabilities=caps)
		self.driver.maximize_window()
		    #self.driver = webdriver.PhantomJS()
		self.driver.set_window_size(1120, 550)

	def scrape_prices(self):
		self.driver.get(link)
		
		element =WebDriverWait(self.driver, 10).until(EC.element_to_be_clickable((By.ID, "101_dealView_2")))
		s = BeautifulSoup(self.driver.page_source,"lxml")
		items = s.findAll("div", {"id" : re.compile('[0-9]_dealView_[0-9]')})
		return items

	def scrape(self):
		source = self.scrape_prices()
		print (source[0])
		self.driver.quit()

if __name__ == '__main__':
    scraper = AmzonScraper()
    scraper.scrape()
