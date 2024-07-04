import React, { useEffect, useRef, useState } from 'react';
import '../csyt-one-bss/csyt-one-bss-filter.scss';
import 'react-toastify/dist/ReactToastify.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFilter } from '@fortawesome/free-solid-svg-icons';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { DateRange } from 'react-date-range';
import { translate } from 'react-jhipster';

const CsytOneBssFilterComponent = ({ onFilterChange }) => {
  const formatDate = date => {
    return moment(date).format('YYYY-MM-DD');
  };

  const formatDateDisplay = date => {
    return moment(date).format('DD/MM/YYYY');
  };

  const [thoiGianCapNhatRange, setThoiGianCapNhatRange] = useState([
    {
      startDate: '',
      endDate: '',
      key: 'selection',
    },
  ]);

  const [ngayKyHopDongRange, setNgayKyHopDongRange] = useState([
    {
      startDate: '',
      endDate: '',
      key: 'selection',
    },
  ]);

  const [isOpenTGCN, setIsOpenTGCN] = useState(false);
  const [isOpenNKHD, setIsOpenNKHD] = useState(false);
  const refTGCN = useRef(null);
  const refNKHD = useRef(null);
  const pressOutsideTGCN = e => {
    if (refTGCN.current && !refTGCN.current.contains(e.target)) {
      // !ref.current.contains(e.target): phần tử click ko nằm trong tham chiếu ref
      setIsOpenTGCN(false);
    }
  };

  const pressOutsideNKHD = e => {
    if (refNKHD.current && !refNKHD.current.contains(e.target)) {
      setIsOpenNKHD(false);
    }
  };

  useEffect(() => {
    document.addEventListener('click', pressOutsideTGCN, true);
    document.addEventListener('click', pressOutsideNKHD, true);
  }, []);

  const [filter, setFilter] = useState({
    maCsyt: '',
    maThuebao: '',
    maDuan: '',
    trangthai: '',
    thoigianCapnhatStart: '',
    thoigianCapnhatEnd: '',
    maTinh: '',
    maSpdv: '',
    maHopdong: '',
    ngaykyHopdongStart: '',
    ngaykyHopdongEnd: '',
  });

  const handleInputChange = e => {
    const { id, value } = e.target;
    setFilter({ ...filter, [id]: value });
  };

  const handleTGCNRange = ranges => {
    setThoiGianCapNhatRange([
      {
        startDate: ranges.selection.startDate,
        endDate: ranges.selection.endDate,
        key: 'selection',
      },
    ]);
    setFilter({
      ...filter,
      thoigianCapnhatStart: formatDate(ranges.selection.startDate),
      thoigianCapnhatEnd: formatDate(ranges.selection.endDate),
    });
  };

  const handleNKHDRange = ranges => {
    setNgayKyHopDongRange([
      {
        startDate: ranges.selection.startDate,
        endDate: ranges.selection.endDate,
        key: 'selection',
      },
    ]);
    setFilter({
      ...filter,
      ngaykyHopdongStart: formatDate(ranges.selection.startDate),
      ngaykyHopdongEnd: formatDate(ranges.selection.endDate),
    });
  };

  const handleSubmit = e => {
    e.preventDefault();
    onFilterChange(filter);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="filter row">
        <div className="left col-lg-2 col-md-4">
          <div className="form-group">
            <label htmlFor="maCsyt">{translate('csytApp.csytOneBss.filter.maCsyt')}</label>
            <input type="text" className="form-control" id="maCsyt" value={filter.maCsyt} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="maThuebao">{translate('csytApp.csytOneBss.filter.maThuebao')}</label>
            <input type="text" className="form-control" id="maThuebao" value={filter.maThuebao} onChange={handleInputChange} />
          </div>
        </div>

        <div className="center col-lg-2 col-md-4 ">
          <div className="form-group">
            <label htmlFor="maDuan">{translate('csytApp.csytOneBss.filter.maDuan')}</label>
            <input type="text" className="form-control" id="maDuan" value={filter.maDuan} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="maTinh">{translate('csytApp.csytOneBss.filter.maTinh')}</label>
            <input type="text" className="form-control" id="maTinh" value={filter.maTinh} onChange={handleInputChange} />
          </div>
        </div>

        <div className="center col-lg-2 col-md-4">
          <div className="form-group">
            <label htmlFor="maSpdv">{translate('csytApp.csytOneBss.filter.maSpdv')}</label>
            <input type="text" className="form-control" id="maSpdv" value={filter.maSpdv} onChange={handleInputChange} />
          </div>
          <div className="form-group">
            <label htmlFor="maHopdong">{translate('csytApp.csytOneBss.filter.maHopdong')}</label>
            <input type="text" className="form-control" id="maHopdong" value={filter.maHopdong} onChange={handleInputChange} />
          </div>
        </div>
        <div className="right col-lg-3 col-md-4">
          <div className="form-group thoiGianCapNhat">
            <label htmlFor="thoigianCapNhat">{translate('csytApp.csytOneBss.filter.thoigianCapnhat')}</label>
            <div className="date-range">
              <input
                readOnly
                className="form-control"
                value={
                  thoiGianCapNhatRange[0].startDate && thoiGianCapNhatRange[0].endDate
                    ? `${formatDateDisplay(thoiGianCapNhatRange[0].startDate)} - ${formatDateDisplay(thoiGianCapNhatRange[0].endDate)}`
                    : ''
                }
                onClick={() => setIsOpenTGCN(true)}
              ></input>
              <div className="range" ref={refTGCN} style={{ display: isOpenTGCN ? 'flex' : 'none' }}>
                <DateRange
                  onChange={handleTGCNRange}
                  editableDataInputs={true}
                  showMonthAndYearPickers={false}
                  showDateDisplay={false}
                  ranges={thoiGianCapNhatRange}
                  months={1}
                  direction="vertical"
                />
              </div>
            </div>
          </div>
          <div className="form-group ngaykyHopdong">
            <label htmlFor="ngaykyHopdong">{translate('csytApp.csytOneBss.filter.ngaykyHopdong')}</label>
            <div className="date-range">
              <input
                readOnly
                className="form-control"
                value={
                  ngayKyHopDongRange[0].startDate && ngayKyHopDongRange[0].endDate
                    ? `${formatDateDisplay(ngayKyHopDongRange[0].startDate)} - ${formatDateDisplay(ngayKyHopDongRange[0].endDate)}`
                    : ''
                }
                onClick={() => setIsOpenNKHD(true)}
              ></input>
              <div className="range" ref={refNKHD} style={{ display: isOpenNKHD ? 'flex' : 'none' }}>
                <DateRange
                  onChange={handleNKHDRange}
                  editableDataInputs={true}
                  showMonthAndYearPickers={false}
                  showDateDisplay={false}
                  ranges={ngayKyHopDongRange}
                  months={1}
                  direction="vertical"
                  rangeColors={['#3d91ff', '#3ecf8e', '#fed14c']}
                />
              </div>
            </div>
          </div>
        </div>
        <div className="right col-lg-2 col-md-4">
          <div className="form-group">
            <label htmlFor="trangThai">{translate('csytApp.csytOneBss.filter.trangthai')}</label>
            <select className="form-select" id="trangthai" onChange={handleInputChange}>
              <option value="">Tất cả</option>
              <option value="0">Khởi tạo</option>
              <option value="1">Gia hạn</option>
              <option value="2">Hủy</option>
            </select>
          </div>
          <button type="submit" onClick={handleSubmit} className="btn btn-secondary col-lg-6 col-sm-4">
            <FontAwesomeIcon className="me-1" icon={faFilter} />
            <span>{translate('csytApp.csytOneBss.filter.filter')}</span>
          </button>
        </div>
      </div>
    </form>
  );
};

export default CsytOneBssFilterComponent;
